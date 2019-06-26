package scenarios;

import kalturaott.OTTUserApi;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestScenario {

    OTTUserApi kalturaOTTUser;

    @Before
    public void intClass(){
        kalturaOTTUser = new OTTUserApi();
        System.out.println("Before");
    }

    @Test
    public void testRegister(){
        kalturaOTTUser.register();
    }

    @Test
    public void testRegisterBad(){
        kalturaOTTUser.registerBad();
    }

    @Test
    public void testLogin() throws IOException {
        kalturaOTTUser.register();
        kalturaOTTUser.login();
    }

    @Test
    public void testLoginBad(){
        kalturaOTTUser.register();
        kalturaOTTUser.loginBad();
    }

    @Test
    public void testUpdate(){
        kalturaOTTUser.register();
        kalturaOTTUser.login();
        kalturaOTTUser.update();
    }

    @Test
    public void testUpdateBad(){
        kalturaOTTUser.register();
        kalturaOTTUser.login();
        kalturaOTTUser.updateBad();

    }
}
