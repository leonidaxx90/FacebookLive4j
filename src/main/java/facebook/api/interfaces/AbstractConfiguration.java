package facebook.api.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import facebook.exception.FacebookException;


/**
 * Created by salvo on 11/05/17.
 */
public abstract class AbstractConfiguration {

    public Properties prop = new Properties();

    public AbstractConfiguration(String path) throws Exception{
        loadConfig(path);
    }

    abstract public String getToken();

    abstract public String getDestinationId();

    abstract public String getApiUrl();

    /**
     * Clear config. In this way is possible read other config file calling loadConfig()
     * @throws Exception
     */
    public void clearConfig() throws Exception {
        this.prop = null;
    }

    /**
     * Load config. In this way is possible read other config file, after will do clearConfig()
     * @throws Exception
     */
    public void loadConfig(String path) throws IOException, FacebookException {

        if(path == null){
            path = "/facebook.properties";
        }

        InputStream input = null;
        input = this.getClass().getResourceAsStream(path);
        if(input != null){
            prop.load(input);
        }else{
            throw new FacebookException("File " + path + "not found. Place this file under your projet package and provide full path (ex: /my/pkg/facebook.properties");
        }
    }
}
