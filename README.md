	# Facebook4j
	#
	############
	# For support ask on http://stackoverflow.com/ and insert facebook4live-api-v1 tags in your question
	############


	/**
	*	MANDATORY OPERATION
	*
	* 	1) Create facebook.properties file (look src/test/resources/facebook.properties) and place it in your project root
	*
	*	2) Import facebook jar file (located in dist folder) in your project
	*
	*	3) Look Example.java for code example.
	**/


	/**
         * ADVANCED CUSTOMIZATION
         */
        
        
        /**
         * If you want provide a different wrapper (not recommend) to call web api you need create a java class
         * extends AbstractFacebookLiveClient implements IFacebookClient. Here provide you custom implementation
         * 
         * If you want provide a different mechnism (not recommend) to read configuration (for example a DB rather a file), you must
         * create a java class and extends AbstractConfiguration. Here provide you custom implementation. In this case you need override loadConfig()
         * 
         * Then call factory with the your full name class's and path to your config file
         */
        IFacebookClient facebookCustom = factoryFacebookLive.getFacebookClient(
                "my.pkg.custom.FacebookLiveImplCustom",
                "my.pkg.custom.ConfigurationImplCustom",
                "/path/to/facebook.properties");

		/**
		*	If you want provide a different wrapper implementation and path properties file
		**/
        IFacebookClient facebook2 = factoryFacebookLive.getFacebookClient(
        		"facebook.api.impl.custom.FacebookLiveImplCustom",
                "/custom/facebook.properties");

        /**
         * Config file is a singleton. If during your execution you need to load different config you need call clearConfig()
         * and after this istruction re-call factory
         */
        factoryFacebookLive.clearConfig();
		
        /**
         * The core code look for config file (facebook.properties) file in root of you project
         * If you want use default implementation but you place your config file not in defualt place, you can call factory in this way
         * 
         */
        IFacebookClient facebook3 = factoryFacebookLive.getFacebookClient("/my/custom/path/facebook.properties");
