package facebook.api.interfaces;

import facebook.bean.BaseLiveVideoRequestBean;
import facebook.bean.BaseLiveVideoResponseBean;

/**
 * Created by salvo on 10/05/17.
 */
public interface IFacebookClient {

    public BaseLiveVideoResponseBean createDefaultLiveVideo() throws Exception;
    public BaseLiveVideoResponseBean createContinuosLiveVideo() throws Exception;
    public BaseLiveVideoResponseBean createScheduledLiveVideo(BaseLiveVideoRequestBean liveVideoBean) throws Exception;

    public BaseLiveVideoResponseBean updateLiveVideo(BaseLiveVideoRequestBean updateLiveVideoBean) throws Exception;

    public BaseLiveVideoResponseBean deleteLiveVideo(String id)throws Exception;

    public BaseLiveVideoResponseBean getLiveVideos()throws Exception;

    public BaseLiveVideoResponseBean getLiveVideo(String id)throws Exception;

}
