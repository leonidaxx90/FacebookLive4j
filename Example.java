package testFaceboojk;

import facebook.api.factory.FactoryFacebookLive;
import facebook.api.interfaces.IFacebookClient;
import facebook.bean.BaseLiveVideoResponseBean;
import facebook.bean.CreateLiveVideoScheduledRequestBean;
import facebook.bean.DeleteLiveVideoResponseBean;
import facebook.bean.GetOrCreateOrUpdateLiveVideoResponseBean;
import facebook.bean.UpdateLiveVideoRequestBean;

public class Example {

	public static void main(String[] args)throws Exception{
		
		/**
		 * Get factory facebook live
		 */
		FactoryFacebookLive factoryFacebookLive = new FactoryFacebookLive();
		
		
		
		/**
		 * Place a file named facebook.properties in root of you project
		 * 
		 * Get facebook client.
		 */
 	        IFacebookClient facebook = factoryFacebookLive.getFacebookClient();
        
        
        
        	/**
        	 * Create default live video
        	 */
        	BaseLiveVideoResponseBean liveVideoCreated = facebook.createDefaultLiveVideo();
        	GetOrCreateOrUpdateLiveVideoResponseBean response = (GetOrCreateOrUpdateLiveVideoResponseBean) liveVideoCreated;
        	System.out.println(response.getId());
        
        

        	/**
        	 * Create scheduled live video
        	 */
        	CreateLiveVideoScheduledRequestBean liveVideoBean = new CreateLiveVideoScheduledRequestBean();
        	liveVideoBean.setPlanned_start_time("1495238400");//time in unix timestamp
        	liveVideoBean.setScheduled_custom_profile_image("/Users/salvo/Desktop/foto.jpg");//optionally
        
        	BaseLiveVideoResponseBean scheduledLiveVideoCreated = facebook.createScheduledLiveVideo(liveVideoBean);
        	GetOrCreateOrUpdateLiveVideoResponseBean response2 = (GetOrCreateOrUpdateLiveVideoResponseBean) scheduledLiveVideoCreated;
        	System.out.println(response2.getId());

        
        
        	/**
        	 * Create continuos live video
        	 */
        	BaseLiveVideoResponseBean continuosLiveVideoCreated = facebook.createContinuosLiveVideo();
        	GetOrCreateOrUpdateLiveVideoResponseBean response3 = (GetOrCreateOrUpdateLiveVideoResponseBean) continuosLiveVideoCreated;
        	System.out.println(response3.getId());
        
        
        
       		/**
        	* Update existing video
        	*/
       		UpdateLiveVideoRequestBean updateLiveVideo = new UpdateLiveVideoRequestBean();
       		updateLiveVideo.setId(response3.getId());	//video id was returned during creation
       		updateLiveVideo.setTitle("New Title");
        	updateLiveVideo.setDescription("New Description");
        
       		BaseLiveVideoResponseBean updatedLiveVideo = facebook.updateLiveVideo(updateLiveVideo);
       		GetOrCreateOrUpdateLiveVideoResponseBean response4 = (GetOrCreateOrUpdateLiveVideoResponseBean) updatedLiveVideo;
        	System.out.println(response4.getId());
        
        
        
        	/**
        	 * Delete existing video
        	 */
        	BaseLiveVideoResponseBean deletedVideo = facebook.deleteLiveVideo(response3.getId());
        	DeleteLiveVideoResponseBean response5 = (DeleteLiveVideoResponseBean) deletedVideo;
        	System.out.println(response5.getSuccess());
     		
	}
}

