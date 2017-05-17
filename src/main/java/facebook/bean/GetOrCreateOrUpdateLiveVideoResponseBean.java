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
    private String embed_html;

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
}
