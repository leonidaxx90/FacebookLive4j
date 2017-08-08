package facebook.bean;

/**
 * Created by salvo on 11/05/17.
 */
public class GetOrCreateOrUpdateLiveVideoResponseBean extends BaseLiveVideoResponseBean{

    private String id;
    private String status;
    private String title;
    private String stream_url;
    private String secure_stream_url;
    private String dash_preview_url;
    private String embed_html;

    private From from;
    private String planned_start_time;
    private Video video;
    private String permalink_url;
    private String seconds_left;
    private String broadcast_start_time;
    private String is_manual_mode;
    private String live_views;
    private String creation_time;
    private String description;

    public GetOrCreateOrUpdateLiveVideoResponseBean(String originalJson){
        super(originalJson);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStream_url() {
        return stream_url;
    }

    public void setStream_url(String stream_url) {
        this.stream_url = stream_url;
    }

    public String getSecureStreamUrl() {
        return secure_stream_url;
    }

    public void setSecureStreamUrl(String secureStreamUrl) {
        this.secure_stream_url = secureStreamUrl;
    }

    public String getEmbed_html() {
        return embed_html;
    }

    public void setEmbed_html(String embed_html) {
        this.embed_html = embed_html;
    }

    public String getSecure_stream_url() {
        return secure_stream_url;
    }

    public void setSecure_stream_url(String secure_stream_url) {
        this.secure_stream_url = secure_stream_url;
    }

    public String getDash_preview_url() {
        return dash_preview_url;
    }

    public void setDash_preview_url(String dash_preview_url) {
        this.dash_preview_url = dash_preview_url;
    }

    //erorr field
    private Error error;

    public static final class Error {
        private final String message;
        private final String type;
        private final long code;
        private final String fbtrace_id;

        public Error(String message, String type, long code, String fbtrace_id){
            this.message = message;
            this.type = type;
            this.code = code;
            this.fbtrace_id = fbtrace_id;
        }

        public String getMessage() {
            return message;
        }

        public String getType() {
            return type;
        }

        public long getCode() {
            return code;
        }

        public String getFbtrace_id() {
            return fbtrace_id;
        }
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public String getPlanned_start_time() {
        return planned_start_time;
    }

    public void setPlanned_start_time(String planned_start_time) {
        this.planned_start_time = planned_start_time;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getPermalink_url() {
        return permalink_url;
    }

    public void setPermalink_url(String permalink_url) {
        this.permalink_url = permalink_url;
    }

    public String getSeconds_left() {
        return seconds_left;
    }

    public void setSeconds_left(String seconds_left) {
        this.seconds_left = seconds_left;
    }

    public String getBroadcast_start_time() {
        return broadcast_start_time;
    }

    public void setBroadcast_start_time(String broadcast_start_time) {
        this.broadcast_start_time = broadcast_start_time;
    }

    public String getIs_manual_mode() {
        return is_manual_mode;
    }

    public void setIs_manual_mode(String is_manual_mode) {
        this.is_manual_mode = is_manual_mode;
    }

    public String getLive_views() {
        return live_views;
    }

    public void setLive_views(String live_views) {
        this.live_views = live_views;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(String creation_time) {
        this.creation_time = creation_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
