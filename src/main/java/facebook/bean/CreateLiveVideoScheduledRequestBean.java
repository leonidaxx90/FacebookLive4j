package facebook.bean;
/**
 * Created by salvo on 10/05/17.
 */
public class CreateLiveVideoScheduledRequestBean extends BaseLiveVideoRequestBean{

    private String planned_start_time;
    private String scheduled_custom_profile_image;
    private String status;
    private String is_manual_mode;

    public String getPlanned_start_time() {
        return planned_start_time;
    }

    public void setPlanned_start_time(String planned_start_time) {
        this.planned_start_time = planned_start_time;
    }

    public String getScheduled_custom_profile_image() {
        return scheduled_custom_profile_image;
    }

    public void setScheduled_custom_profile_image(String scheduled_custom_profile_image) {
        this.scheduled_custom_profile_image = scheduled_custom_profile_image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_manual_mode() {
        return is_manual_mode;
    }

    public void setIs_manual_mode(String is_manual_mode) {
        this.is_manual_mode = is_manual_mode;
    }
}
