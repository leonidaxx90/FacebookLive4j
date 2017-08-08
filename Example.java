import facebook.api.factory.FactoryFacebookLive;
import facebook.api.interfaces.IFacebookClient;
import facebook.bean.BaseLiveVideoResponseBean;
import facebook.bean.CreateLiveVideoScheduledRequestBean;
import facebook.bean.DeleteLiveVideoResponseBean;
import facebook.bean.GetOrCreateOrUpdateLiveVideoResponseBean;
import facebook.bean.LiveVideosResponseBean;
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
        	 * 
        	 * response.getStream_url() return the url rtmp.
       	  	 * 
         	 * Use this in your encoder, for example Wirecast and send video data on this video!!!
           	 */
        	System.out.println(response.getStream_url());
        
        

        	/**
        	 * Create scheduled live video
        	 */
        	UpdateLiveVideoRequestBean liveVideoBean = new UpdateLiveVideoRequestBean();
            liveVideoBean.setPlanned_start_time("1502794800");//Max is 7 days ahead
            liveVideoBean.setScheduled_custom_profile_image("/path/to/my/foto.jpg");
            BaseLiveVideoResponseBean scheduledLiveVideoCreated = null;
            scheduledLiveVideoCreated = facebook.createScheduledLiveVideo(liveVideoBean);
            GetOrCreateOrUpdateLiveVideoResponseBean response = (GetOrCreateOrUpdateLiveVideoResponseBean) scheduledLiveVideoCreated;
    	
       		/**
        	 * 
        	 * response.getStream_url() return the url rtmp.
        	 * 
        	 * Use this in your encoder, for example Wirecast and send video data on this video!!!
        	 */
        	System.out.println(response.getStream_url());

        
        
        	/**
        	 * Create continuos live video
        	 */
        	BaseLiveVideoResponseBean continuosLiveVideoCreated = facebook.createContinuosLiveVideo();
        	GetOrCreateOrUpdateLiveVideoResponseBean response3 = (GetOrCreateOrUpdateLiveVideoResponseBean) continuosLiveVideoCreated;
        	System.out.println(response3.getId());
        	/**
        	 * 
        	 * response.getStream_url() return the url rtmp.
         	 *  
        	 * Use this in your encoder, for example Wirecast and send video data on this video!!!
        	 */
        	System.out.println(response.getStream_url());
        
        
        
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
        
        
        
       		/**
       		  * Get Live Video
        	 */
        	BaseLiveVideoResponseBean liveVideo =  facebook.getLiveVideo(response4.getId());
        	GetOrCreateOrUpdateLiveVideoResponseBean response6 = (GetOrCreateOrUpdateLiveVideoResponseBean) liveVideo;
        	System.out.println(response6.getId());
        	
        
        
        	/**
        	 * Get all Live Videos
        	 */
        	BaseLiveVideoResponseBean liveVideos =  facebook.getLiveVideos();
        	LiveVideosResponseBean response7 = (LiveVideosResponseBean) liveVideos; 
        	/**
        	 * loop on response7 ...
        	 */
		
		
	}
}

