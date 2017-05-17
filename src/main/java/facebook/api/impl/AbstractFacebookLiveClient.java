package facebook.api.impl;

import facebook.api.interfaces.AbstractConfiguration;

/**
 * Created by salvo on 11/05/17.
 */
public class AbstractFacebookLiveClient {

    AbstractConfiguration config;

    protected AbstractFacebookLiveClient(AbstractConfiguration config){
        this.config = config;
    }
}
