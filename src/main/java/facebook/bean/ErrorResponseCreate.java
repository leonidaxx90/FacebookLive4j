package facebook.bean;

/**
 * Created by salvo on 22/05/17.
 */
public class ErrorResponseCreate extends BaseLiveVideoResponseBean {

    public String message;
    public String type;
    public long code;
    public String fbtrace_id;

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

    public ErrorResponseCreate(String originalJson) {
        super(originalJson);
    }
}
