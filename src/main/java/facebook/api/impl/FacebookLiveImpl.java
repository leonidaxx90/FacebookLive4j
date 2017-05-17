package facebook.api.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import facebook.api.interfaces.AbstractConfiguration;
import facebook.api.interfaces.IFacebookClient;
import facebook.bean.BaseLiveVideoRequestBean;
import facebook.bean.BaseLiveVideoResponseBean;
import facebook.bean.CreateLiveVideoScheduledRequestBean;
import facebook.bean.DeleteLiveVideoResponseBean;
import facebook.bean.GetOrCreateOrUpdateLiveVideoResponseBean;
import facebook.bean.LiveVideosResponseBean;
import facebook.bean.UpdateLiveVideoRequestBean;
import facebook.exception.FacebookException;
import facebook.http.CustomHttpClient;
import facebook.http.bean.HttpResponseBean;

/**
 * Created by salvo on 09/05/17.
 */
public class FacebookLiveImpl extends AbstractFacebookLiveClient implements IFacebookClient {

    private CustomHttpClient client;
    private final static String LIVE_VIDEOS_API = "/live_videos";

    public FacebookLiveImpl(AbstractConfiguration config){
        super(config);
        client = CustomHttpClient.getInstance();
    }

    @Override
    public BaseLiveVideoResponseBean createDefaultLiveVideo() throws Exception{
        String token = config.getToken();
        String destId = config.getDestinationId();
        Map<String, String> postParams = new HashMap<>();
        Map<String, File> postFileParams = new HashMap();
        postParams.put("access_token", token);

        HttpResponseBean responseBean = client.doPost(config.getApiUrl() + "/" + config.getDestinationId() + LIVE_VIDEOS_API, null, postParams, null);

        Gson gson = new Gson();
        GetOrCreateOrUpdateLiveVideoResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), GetOrCreateOrUpdateLiveVideoResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());

        if(jsonBean.getId() != null){
            jsonBean = getLiveVideo(jsonBean.getId());
        }

        return jsonBean;
    }

    @Override
    public BaseLiveVideoResponseBean createContinuosLiveVideo() throws Exception{
        String token = config.getToken();
        String destId = config.getDestinationId();
        Map<String, String> postParams = new HashMap<>();
        Map<String, File> postFileParams = new HashMap();
        postParams.put("access_token", token);
        postParams.put("strem_type", "AMBIENT");
        HttpResponseBean responseBean = client.doPost(config.getApiUrl() + "/" + config.getDestinationId() + LIVE_VIDEOS_API, null, postParams, postFileParams);
        Gson gson = new Gson();
        GetOrCreateOrUpdateLiveVideoResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), GetOrCreateOrUpdateLiveVideoResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());

        if(jsonBean.getId() != null){
            jsonBean = getLiveVideo(jsonBean.getId());
        }

        return jsonBean;
    }

    @Override
    public BaseLiveVideoResponseBean createScheduledLiveVideo(BaseLiveVideoRequestBean baseVideoBean) throws Exception{

        CreateLiveVideoScheduledRequestBean updateLiveVideo = (CreateLiveVideoScheduledRequestBean) baseVideoBean;
        String token = config.getToken();
        String destId = config.getDestinationId();
        Map<String, String> postParams = new HashMap<>();
        Map<String, File> postFileParams = new HashMap();
        postParams.put("access_token", token);

        /**
         * param for schedule video
         */
        if(updateLiveVideo.getPlanned_start_time() == null){
            throw new FacebookException("'planned_start_time' param is mandatory in scheduled video. Set this with unix timestamp");
        }

        postParams.put("planned_start_time", updateLiveVideo.getPlanned_start_time()); //149459990

        if(updateLiveVideo.getStatus() != null){
            postParams.put("status", updateLiveVideo.getStatus());
        }

        if(updateLiveVideo.getScheduled_custom_profile_image() != null) {
            postFileParams.put("schedule_custom_profile_image", new File(updateLiveVideo.getScheduled_custom_profile_image()));
        }

        if(updateLiveVideo.getIs_manual_mode() != null){
            postParams.put("is_manual_mode", updateLiveVideo.getIs_manual_mode());
        }

        HttpResponseBean responseBean = client.doPost(config.getApiUrl() + "/" + config.getDestinationId() + LIVE_VIDEOS_API, null, postParams, postFileParams);

        Gson gson = new Gson();
        GetOrCreateOrUpdateLiveVideoResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), GetOrCreateOrUpdateLiveVideoResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());

        if(jsonBean.getId() != null){
            jsonBean = getLiveVideo(jsonBean.getId());
        }

        return jsonBean;

    }


    @Override
    public BaseLiveVideoResponseBean updateLiveVideo(BaseLiveVideoRequestBean baseLiveVideoBean) throws Exception {

        UpdateLiveVideoRequestBean updateLiveVideo = (UpdateLiveVideoRequestBean) baseLiveVideoBean;
        String token = config.getToken();
        String destId = config.getDestinationId();
        Map<String, String> postParams = new HashMap<>();
        Map<String, File> postFileParams = new HashMap();
        postParams.put("access_token", token);

        Field[] fields = UpdateLiveVideoRequestBean.class.getDeclaredFields();
        Object obj = updateLiveVideo;
        for (Field field : fields) {

            String fieldValue = (String) updateLiveVideo.getClass().getMethod("get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1)).invoke(obj);
            if(fieldValue != null) {
                if(fieldValue.equals("scheduled_custom_profile_image")) {
                    postFileParams.put(field.getName(), new File(field.getName()));
                }else{
                    postParams.put(field.getName(), fieldValue);
                }
            }
        }

        HttpResponseBean responseBean = client.doPost(config.getApiUrl() + "/" + updateLiveVideo.getId(), null, postParams, postFileParams);

        Gson gson = new Gson();
        GetOrCreateOrUpdateLiveVideoResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), GetOrCreateOrUpdateLiveVideoResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());

        if(jsonBean.getId() != null){
            jsonBean = getLiveVideo(jsonBean.getId());
        }

        return jsonBean;


    }

    @Override
    public DeleteLiveVideoResponseBean deleteLiveVideo(String id) throws Exception{

        String token = config.getToken();
        String destId = config.getDestinationId();
        List<String> queryParams = new ArrayList<String>();
        queryParams.add("access_token=" + token);

        HttpResponseBean responseBean = client.doDelete(config.getApiUrl() + '/' +id, queryParams);

        Gson gson = new Gson();
        DeleteLiveVideoResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), DeleteLiveVideoResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());
        jsonBean.setOriginalJson(responseBean.getResponse());
        return jsonBean;

    }

    @Override
    public BaseLiveVideoResponseBean getLiveVideos() throws Exception {
        String token = config.getToken();
        String destId = config.getDestinationId();
        List<String> queryParams = new ArrayList<String>();
        queryParams.add("access_token=" + token);

        HttpResponseBean responseBean = client.doGet(config.getApiUrl() + '/' + config.getDestinationId() + LIVE_VIDEOS_API, queryParams);

        Gson gson = new Gson();
        LiveVideosResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), LiveVideosResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());
        jsonBean.setOriginalJson(responseBean.getResponse());
        return jsonBean;

    }

    @Override
    public GetOrCreateOrUpdateLiveVideoResponseBean getLiveVideo(String id) throws Exception {
        String token = config.getToken();
        String destId = config.getDestinationId();
        List<String> queryParams = new ArrayList<String>();
        queryParams.add("access_token=" + token);

        HttpResponseBean responseBean = client.doGet(config.getApiUrl() + '/' + id, queryParams);

        Gson gson = new Gson();
        GetOrCreateOrUpdateLiveVideoResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), GetOrCreateOrUpdateLiveVideoResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());
        jsonBean.setOriginalJson(responseBean.getResponse());
        return jsonBean;
    }

}
