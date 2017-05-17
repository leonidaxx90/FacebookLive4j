package facebook.api.impl.custom;

import facebook.api.impl.AbstractFacebookLiveClient;
import facebook.api.interfaces.AbstractConfiguration;
import facebook.api.interfaces.IFacebookClient;
import facebook.bean.BaseLiveVideoRequestBean;
import facebook.bean.BaseLiveVideoResponseBean;

/**
 * Created by salvo on 12/05/17.
 */
public class FacebookLiveImplCustom extends AbstractFacebookLiveClient implements IFacebookClient{

    public FacebookLiveImplCustom(AbstractConfiguration config){
        super(config);
    }
    @Override
    public BaseLiveVideoResponseBean createDefaultLiveVideo() throws Exception {
        return null;
    }

    @Override
    public BaseLiveVideoResponseBean createContinuosLiveVideo() throws Exception {
        return null;
    }

    @Override
    public BaseLiveVideoResponseBean createScheduledLiveVideo(BaseLiveVideoRequestBean liveVideoBean) throws Exception {
        return null;
    }

    @Override
    public BaseLiveVideoResponseBean updateLiveVideo(BaseLiveVideoRequestBean updateLiveVideoBean) throws Exception {
        return null;
    }

    @Override
    public BaseLiveVideoResponseBean deleteLiveVideo(String id) throws Exception {
        return null;
    }

    @Override
    public BaseLiveVideoResponseBean getLiveVideos() throws Exception {
        return null;
    }

    @Override
    public BaseLiveVideoResponseBean getLiveVideo(String id) throws Exception {
        return null;
    }
}
