package facebook.api.impl;

import facebook.api.interfaces.AbstractConfiguration;

/**
 * Created by salvo on 11/05/17.
 */
public class ConfigurationImpl extends AbstractConfiguration {

    private static ConfigurationImpl conf;

    private ConfigurationImpl(String path) throws Exception{
        super(path);
    }

    public static ConfigurationImpl getInstance(String path) throws Exception{
        if(conf == null || conf.prop == null/*a seguito di clear config*/){
            conf = new ConfigurationImpl(path);
        }else{
            return conf;
        }
        return conf;
    }

    @Override
    public String getToken(){
        return prop.getProperty("token");
    }

    @Override
    public String getDestinationId(){
        return prop.getProperty("dest_id");
    }

    @Override
    public String getApiUrl(){
        return prop.getProperty("api_url");
    }


}
