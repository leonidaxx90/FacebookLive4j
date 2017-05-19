

# FacebookLive4j (Create Facebook Live Direct from java code)

 With this library/framework you can create a FacebookLive video via code, using java. When you create a Live Video this will be showed in your page/profile
 To use this library you first need to generate an access token (look _TOKEN.md_)

![](https://www.forexinfo.it/local/cache-vignettes/L620xH313/arton39304-93f9f.jpg?1495036366)
### For support ask on http://stackoverflow.com/ and insert _facebooklive4j-api-v1_ tags in your question


# Installation

 1) Create facebook.properties file (look _src/test/resources/facebook.properties_) and place it in your project root

 2) Import facebook jar file (located in dist folder) in your project

 3) Look Example.java for code example.


# Advanced Customization
  If you want provide a different wrapper (not recommend) to call web api you need create a java class
  extends _AbstractFacebookLiveClient_ and implements _IFacebookClient_. Here provide you custom implementation
  If you want provide a different mechnism (not recommend) to read configuration (for example a DB rather a file), you must
  create a java class and extends AbstractConfiguration. Here provide your custom implementation. In this case you need override loadConfig()
  Then call factory with the your full name class's and path to your config file and populate _prop_ hashmap (look _src/main/java/facebook/api/interfaces/AbstractConfiguration.java_)
  ```      
        IFacebookClient facebookCustom = factoryFacebookLive.getFacebookClient(
                "my.pkg.custom.FacebookLiveImplCustom",
                "my.pkg.custom.ConfigurationImplCustom",
                "/path/to/facebook.properties");

  ```
 
  If you want provide a different wrapper implementation and path properties file

   ```
        IFacebookClient facebook2 = factoryFacebookLive.getFacebookClient(
        		"facebook.api.impl.custom.FacebookLiveImplCustom",
                "/custom/facebook.properties");
   ```
  
  ## Reload _facebook.properties_ file 
  Config file is a singleton. If during your execution you need to load different config you need call clearConfig()
  and after this istruction re-call factory
  
  ``` 
   factoryFacebookLive.clearConfig();
   IFacebookClient facebook3 = factoryFacebookLive.getFacebookClient("/facebook.properties_2");
  ```		
        
  To default the core code look for config file (facebook.properties) file in root of you project
  If you want use default implementation but you place your config file not in defualt place, you can call factory in this way
         
  ```      
  IFacebookClient facebook3 = factoryFacebookLive.getFacebookClient("/my/custom/path/facebook.properties");
  ```
