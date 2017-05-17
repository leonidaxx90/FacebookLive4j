package facebook.bean;
/**
 * Created by salvo on 12/05/17.
 */
public class LiveVideosResponseBean extends BaseLiveVideoResponseBean{

    GetOrCreateOrUpdateLiveVideoResponseBean data[];

    LiveVideosResponseBean(String originalJson){
        super(originalJson);
    }

    public GetOrCreateOrUpdateLiveVideoResponseBean[] getData() {
        return data;
    }

    public void setData(GetOrCreateOrUpdateLiveVideoResponseBean[] data) {
        this.data = data;
    }
}
