package facebook.bean;

/**
 * Created by salvo on 12/05/17.
 */
public class BaseLiveVideoResponseBean {

    private String originalJson;

    public BaseLiveVideoResponseBean(String originalJson) {
        this.originalJson = originalJson;
    }

    public String getOriginalJson() {
        return originalJson;
    }

    public void setOriginalJson(String originalJson) {
        this.originalJson = originalJson;
    }

    @Override
    public String toString(){
        return getOriginalJson();
    }
}
