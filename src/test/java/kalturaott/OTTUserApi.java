package kalturaott;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import restutil.RestRequest;
import restutil.RestResponse;

import java.io.IOException;

public class OTTUserApi {

    static String actionDomain = "https://rest-eus1.ott.kaltura.com/restful_v5_0/api_v3/service/ottuser/action";
    static String uriRegister = actionDomain + "/register";
    static String uriLogin = actionDomain + "/login";
    static String uriUpdate = actionDomain + "/update";

    public KalturaUser user;
    RestResponse restResponse;


    public void register(){
        user = new KalturaUser().generateKalturaUser();
        restResponse  = new RestRequest().sendRequest(uriRegister, user);
        assert restResponse.isSuccessful();
        getExtentionCode(restResponse.body);
    }

    public void registerBad(){
        user = new KalturaUser().generateKalturaUser();
        user.setPartnerId(0);
        restResponse  = new RestRequest().sendRequest(uriRegister, user);
        assert isContainingError(restResponse.body);
    }

    private boolean isContainingError(String json){
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonElement jr = jsonObject.get("result");
        JsonObject jrObj = jr.getAsJsonObject();
        JsonElement externalId = jrObj.get("error");
        if ( externalId == null )
            return false;
        return true;
    }

    private String getExtentionCode(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonElement jr = jsonObject.get("result");
        JsonObject jrObj = jr.getAsJsonObject();
        JsonElement externalId = jrObj.get("externalId");
        System.out.println("Extention Id: " + externalId);
        return externalId.toString();
    }

    private void printBody(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseString);
    }


    public void login() {
        KalturaUserLogin kalturaUserLogin = new KalturaUserLogin(user);
        restResponse  = new RestRequest().sendRequest(uriLogin, kalturaUserLogin);
        assert ! isContainingError(restResponse.body);
        user.setToken( getTokenCode(restResponse.body) );


    }

    public void loginBad() {
        KalturaUserLogin kalturaUserLogin = new KalturaUserLogin(user);
        kalturaUserLogin.setPassword("badPassword");
        restResponse  = new RestRequest().sendRequest(uriLogin, kalturaUserLogin);
        assert  isContainingError(restResponse.body);

    }

    private String getTokenCode(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonElement jr = jsonObject.get("result");
        JsonObject jrObj = jr.getAsJsonObject();
        JsonElement loginSession = jrObj.get("loginSession");
        JsonObject loginSessionObj = loginSession.getAsJsonObject();
        JsonElement token = loginSessionObj.get("ks");
        System.out.println("Token is : " + token);
        if (token != null)
            return token.toString();
        return null;

    }

    private void wasNewNameUpdated(String json, String newName) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonElement jr = jsonObject.get("result");
        JsonObject jrObj = jr.getAsJsonObject();
        JsonElement userName = jrObj.get("userName");
        assert newName.equals(userName.toString());
    }


    public void update() {
        user.setUsername("testName");
        restResponse  = new RestRequest().sendRequest(uriUpdate, user);
        assert restResponse.isSuccessful();
        getExtentionCode(restResponse.body);
        wasNewNameUpdated(restResponse.body,"testName");
    }

    public void updateBad() {
        user.setUsername(null);
        restResponse  = new RestRequest().sendRequest(uriUpdate, user);
        assert restResponse.isSuccessful();
        assert ! isContainingError(restResponse.body);
    }
}
