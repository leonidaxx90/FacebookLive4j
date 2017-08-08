package facebook.bean;

/**
 * Created by salvo on 11/05/17.
 */

/**
 * NB: field must be called exactly as facebook documentation. Default client implementation use reflection
 */
public class UpdateLiveVideoRequestBean extends BaseLiveVideoRequestBean{

    private String id;
    private String description;
    private String disturbing;
    private String embeddable;
    private String end_live_video;
    private String is_manual_mode;
    private String planned_start_time;
    private String place;
    private String privacy;
    private String scheduled_custom_profile_image;
    private String status;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisturbing() {
        return disturbing;
    }

    public void setDisturbing(String disturbing) {
        this.disturbing = disturbing;
    }

    public String getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(String embeddable) {
        this.embeddable = embeddable;
    }

    public String getEnd_live_video() {
        return end_live_video;
    }

    public void setEnd_live_video(String end_live_video) {
        this.end_live_video = end_live_video;
    }

    public String getIs_manual_mode() {
        return is_manual_mode;
    }

    public void setIs_manual_mode(String is_manual_mode) {
        this.is_manual_mode = is_manual_mode;
    }

    public String getPlanned_start_time() {
        return planned_start_time;
    }

    public void setPlanned_start_time(String planned_start_time) {
        this.planned_start_time = planned_start_time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
