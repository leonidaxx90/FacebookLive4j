package facebook.http.bean;

/**
 * Created by salvo on 09/05/17.
 */
public class HttpResponseBean {

    private String response;
    private int responseCode;

    public HttpResponseBean(String response, int responseCode) {
        this.response = response;
        this.responseCode = responseCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
