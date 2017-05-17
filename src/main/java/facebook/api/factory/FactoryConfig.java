package facebook.api.factory;

import java.lang.reflect.Constructor;

import facebook.api.impl.ConfigurationImpl;
import facebook.api.interfaces.AbstractConfiguration;

/**
 * Created by salvo on 09/05/17.
 */
public class FactoryConfig {

    AbstractConfiguration config;

    /**
     * Return default implementation with default config file path
     * @return
     * @throws Exception
     */
    public AbstractConfiguration getFacebookConfig() throws Exception{
        config = ConfigurationImpl.getInstance(null);
        return config;
    }

    /**
     * Return default implementation with custom config file path
     * @param path
     * @return
     * @throws Exception
     */
    public AbstractConfiguration getFacebookConfig(String path) throws Exception{
        config = ConfigurationImpl.getInstance(path);
        return config;
    }

    /**
     * Return custom implementation with custom config file path
     * @param fullClassName
     * @param path
     * @return
     * @throws Exception
     */
    public AbstractConfiguration getFacebookConfig(String fullClassName, String path) throws Exception{
        Class<?> clazz = Class.forName(fullClassName); //ex: com.impl.MyClass
        Constructor<?> cons = clazz.getConstructor(String.class);
        config = (AbstractConfiguration) cons.newInstance(path);

        return config;
    }

}
