package java.com.orangehrm.utils;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:properties/${TEST_CONFIG}.properties"})
public interface PropertiesConfig extends Config {

    @Key("VIEWPORT_SIZE_X")
    @DefaultValue("1366")
    int viewPortSizeX();

    @Key("VIEWPORT_SIZE_Y")
    @DefaultValue("768")
    int viewPortSizeY();

    @Key("SLOWMO")
    @DefaultValue("200")
    int slowmo();

    @Key("MAX_ELEMENT_TIMEOUT_IN_SEC")
    int maxElementTimeoutInSec();

    @Key("MAX_PAGE_LOAD_TIMEOUT_IN_SEC")
    int maxPageLoadTimeoutInSec();

    @Key("ORANGEHRM_PORTAL_URL")
    String orangeHrmPortalUrl();

    @Key("KEEP_BROWSER_OPEN")
    boolean keepBrowserOpen();

    @Key("BROWSER_TYPE")
    String browserType();

    @Key("REQUEST_JSON_PATH")
    String requestJsonPath();

    @Key("BASE_API_URL")
    String baseApiUrl();

}