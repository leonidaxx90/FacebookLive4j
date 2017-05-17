package facebook.bean;
/**
 * Created by salvo on 12/05/17.
 */
public class DeleteLiveVideoResponseBean extends BaseLiveVideoResponseBean{

    private String success;

    public DeleteLiveVideoResponseBean(String originalJson){
        super(originalJson);
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
