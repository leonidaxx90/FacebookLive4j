package facebook.api.impl.custom;

import facebook.api.interfaces.AbstractConfiguration;

/**
 * Created by salvo on 12/05/17.
 */
public class ConfigurationImplCustom extends AbstractConfiguration {

    public ConfigurationImplCustom(String path) throws Exception{
        super(path);
    }

    @Override
    public String getToken() {
        return "aaasssddd";
    }

    @Override
    public String getDestinationId() {
        return "123456789";
    }

    @Override
    public String getApiUrl() {
        return "http://custom.com/1.0";
    }
}
