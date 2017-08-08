package facebook.api.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

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

    /**
     * Field selected from query on graph api
     */
    private String FIELDS = "dash_preview_url,creation_time,description,from,planned_start_time,status,video,title,stream_url,secure_stream_url,id,permalink_url,seconds_left,broadcast_start_time,embed_html,is_manual_mode,live_views";

    /**
     * Fetch params graph api. Every request limit result to this value.
     */
    private final static String LIMIT = "20";

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

        UpdateLiveVideoRequestBean updateLiveVideo = (UpdateLiveVideoRequestBean) baseVideoBean;
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

        Object obj = updateLiveVideo;
        Field[] fields = (Field[]) getBeanField(CreateLiveVideoScheduledRequestBean.class, false);
        String fieldName;
        String fieldValue;

        for (Field field : fields) {
            fieldName = field.getName();
            fieldValue = (String) updateLiveVideo.getClass().getMethod("get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1)).invoke(obj);

            if(fieldValue != null) {
                if (fieldName.equalsIgnoreCase("scheduled_custom_profile_image")) {
                    postFileParams.put("schedule_custom_profile_image", new File(fieldValue));
                } else {
                    postParams.put(fieldName, fieldValue);
                }
            }
        }

        HttpResponseBean responseBean = client.doPost(config.getApiUrl() + "/" + config.getDestinationId() + LIVE_VIDEOS_API, null, postParams, postFileParams);

        Gson gson = new Gson();
        GetOrCreateOrUpdateLiveVideoResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), GetOrCreateOrUpdateLiveVideoResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());

        //some field not setted when live video is created (is bug facebook?). Is need permorm an update
        updateLiveVideo.setId(jsonBean.getId());

        //if pass planned_star_time and is_manual_mode during update api return with error
        if(updateLiveVideo.getIs_manual_mode() != null && updateLiveVideo.getIs_manual_mode().equalsIgnoreCase("true")) {
            updateLiveVideo.setPlanned_start_time(null);
        }
        updateLiveVideo(updateLiveVideo);

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

        /**
         * I can use Fields[] object or simple ArrayList
         */
        Field[] fields = (Field[]) getBeanField(UpdateLiveVideoRequestBean.class, false);
        Object obj = updateLiveVideo;
        for (Field field : fields) {

            String fieldValue = (String) updateLiveVideo.getClass().getMethod("get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1)).invoke(obj);
            if(fieldValue != null) {
                if(fieldValue.equals("scheduled_custom_profile_image")) {
                    postFileParams.put("schedule_custom_profile_image", new File(fieldValue));
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
        queryParams.add("limit=" + LIMIT);
        queryParams.add("fields=" + FIELDS);


        HttpResponseBean responseBean = client.doGet(config.getApiUrl() + '/' + config.getDestinationId() + LIVE_VIDEOS_API, queryParams);
        HttpResponseBean afterResponse;

        Gson gson = new Gson();
        LiveVideosResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), LiveVideosResponseBean.class);
        LiveVideosResponseBean afterJsonBean = jsonBean;

        /**
         * check if are next page
         */
        boolean empty = false;
        while(!empty) {
            if (afterJsonBean.getPaging() != null) {
                if (afterJsonBean.getPaging().getNext() != null) {
                    queryParams.add("after=" + afterJsonBean.getPaging().getCursors().getAfter());
                    afterResponse = client.doGet(config.getApiUrl() + '/' + config.getDestinationId() + LIVE_VIDEOS_API, queryParams);
                    afterJsonBean = gson.fromJson(afterResponse.getResponse(), LiveVideosResponseBean.class);

                    //merge 2 array
                    jsonBean.setData(ArrayUtils.addAll(jsonBean.getData(), afterJsonBean.getData()));
                }else{
                    empty = true;
                }
            }else {
                empty = true;
            }
        }


        jsonBean.setOriginalJson(gson.toJson(jsonBean));
        return jsonBean;

    }

    @Override
    public GetOrCreateOrUpdateLiveVideoResponseBean getLiveVideo(String id) throws Exception {
        String token = config.getToken();
        String destId = config.getDestinationId();
        List<String> queryParams = new ArrayList<String>();
        queryParams.add("access_token=" + token);
        queryParams.add("fields=" + FIELDS);

        HttpResponseBean responseBean = client.doGet(config.getApiUrl() + '/' + id, queryParams);

        Gson gson = new Gson();
        GetOrCreateOrUpdateLiveVideoResponseBean jsonBean = gson.fromJson(responseBean.getResponse(), GetOrCreateOrUpdateLiveVideoResponseBean.class);
        jsonBean.setOriginalJson(responseBean.getResponse());
        jsonBean.setOriginalJson(responseBean.getResponse());
        return jsonBean;
    }

    /**
     * Return all fields of Class. If asSimpleArrayList is false return Fields[] else return simple array list with fields name
     * @param klazz
     * @param asSimpleArrayList
     * @param <T>
     * @return
     */
    private <T> Object getBeanField(Class<T> klazz, boolean asSimpleArrayList) {
        Field[] fields = klazz.getDeclaredFields();
        ArrayList<String> _fields = new ArrayList<String>(fields.length);

        for (Field field : fields) {
            _fields.add(field.getName());
        }
        if(asSimpleArrayList) {
            return _fields;
        }else{
            return fields;
        }
    }

}
