
import facebook.api.factory.FactoryFacebookLive;
import facebook.api.interfaces.IFacebookClient;
import facebook.bean.BaseLiveVideoResponseBean;
import facebook.bean.GetOrCreateOrUpdateLiveVideoResponseBean;
import facebook.bean.UpdateLiveVideoRequestBean;

public class main {

	public static void main(String [] args) throws Exception{
		FactoryFacebookLive factoryFacebookLive = new FactoryFacebookLive();
		IFacebookClient facebook = factoryFacebookLive.getFacebookClient();
		
		
		UpdateLiveVideoRequestBean liveVideoBean = new UpdateLiveVideoRequestBean();
        liveVideoBean.setPlanned_start_time("1502794800");
        liveVideoBean.setScheduled_custom_profile_image("/Users/salvo/Desktop/foto.jpg");
        BaseLiveVideoResponseBean scheduledLiveVideoCreated = null;
        scheduledLiveVideoCreated = facebook.createScheduledLiveVideo(liveVideoBean);
        GetOrCreateOrUpdateLiveVideoResponseBean response = (GetOrCreateOrUpdateLiveVideoResponseBean) scheduledLiveVideoCreated;
		
		

    	
        
	}
}