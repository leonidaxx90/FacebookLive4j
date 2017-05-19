package facebook.api.factory;

import java.lang.reflect.Constructor;

import facebook.api.impl.FacebookLiveImpl;
import facebook.api.interfaces.AbstractConfiguration;
import facebook.api.interfaces.IFacebookClient;
import facebook.exception.FacebookException;

/**
 * Created by salvo on 09/05/17.
 */
public class FactoryFacebookLive {

    FactoryConfig factoryConfig;
    AbstractConfiguration config;

    public FactoryFacebookLive() {
        factoryConfig = new FactoryConfig();
    }

    /**
     * Return default facebookClient impl with defaul config file impl
     *
     * @return
     * @throws Exception
     */
    public IFacebookClient getFacebookClient() throws Exception {
        config = factoryConfig.getFacebookConfig();
        IFacebookClient facebookClient = new FacebookLiveImpl(config);

        return facebookClient;
    }

    /**
     * Return custom facebookClient impl with default config file impl and custom configFilePath or default configFilePath
     *
     * @param fullClassNameFacebookClient
     * @return
     * @throws Exception
     */
    public IFacebookClient getFacebookClient(String fullClassNameFacebookClient, String pathConfigFile) throws Exception {

        if(pathConfigFile != null) {
            config = factoryConfig.getFacebookConfig(pathConfigFile);
        }else{
            config = factoryConfig.getFacebookConfig();
        }

        Class<?> clazz = Class.forName(fullClassNameFacebookClient);
        Constructor<?> constructor = clazz.getConstructor(AbstractConfiguration.class);
        IFacebookClient facebookClient = (IFacebookClient) constructor.newInstance(config);

        return facebookClient;

    }
    
    /**
     * Return custom facebookClient impl with default config file impl and custom configFilePath or default configFilePath
     *
     * @param pathConfigFile
     * @return
     * @throws Exception
     */
    public IFacebookClient getFacebookClient(String pathConfigFile) throws Exception {

    	if(pathConfigFile != null) {
            config = factoryConfig.getFacebookConfig(pathConfigFile);
        }else{
            throw new FacebookException("Path cannot be null");
        }

        IFacebookClient facebookClient = new FacebookLiveImpl(config);

        return facebookClient;

    }

    /**
     * Return custom facebookClient impl with custom config file impl
     *
     * @param fullClassNameFacebookClient
     * @param fullClassNameConfig
     * @return
     * @throws Exception
     */
    public IFacebookClient getFacebookClient(String fullClassNameFacebookClient, String fullClassNameConfig, String configPath) throws Exception {

        if (fullClassNameConfig != null && fullClassNameFacebookClient != null && configPath != null) {
            config = factoryConfig.getFacebookConfig(fullClassNameConfig, configPath);


            Class<?> clazz = Class.forName(fullClassNameFacebookClient);
            Constructor<?> constructor = clazz.getConstructor(AbstractConfiguration.class);
            IFacebookClient facebookClient = (IFacebookClient) constructor.newInstance(config);

            return facebookClient;
        } else {
            return null;
        }

    }


    /**
     * Clear config
     * @throws Exception
     */
    public void clearConfig() throws Exception {
        factoryConfig.config.clearConfig();
    }
}
