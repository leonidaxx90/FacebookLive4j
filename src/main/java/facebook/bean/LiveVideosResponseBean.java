package facebook.bean;

/**
 * Created by salvo on 12/05/17.
 */
public class LiveVideosResponseBean extends BaseLiveVideoResponseBean{

    GetOrCreateOrUpdateLiveVideoResponseBean data[];
    Paging paging;

    LiveVideosResponseBean(String originalJson){
        super(originalJson);
    }

    public GetOrCreateOrUpdateLiveVideoResponseBean[] getData() {
        return data;
    }

    public void setData(GetOrCreateOrUpdateLiveVideoResponseBean[] data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
