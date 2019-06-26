package restutil;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RestRequest {

    private Gson gson = new Gson();

    public RestResponse sendRequest(String url, Object entity) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);

            StringEntity jsonEntity = new StringEntity(gson.toJson(entity));
            request.addHeader("content-type", "application/json");
            request.setEntity(jsonEntity);

            System.out.println(request);
            HttpResponse response = httpClient.execute(request);
            assertStatus(response);

            return (new RestResponse(response));

        } catch (Exception ex) {
            System.out.print("Exception!!!!" + ex);

        } finally {
        }
        return null;
    }

    private void assertStatus(HttpResponse response) {
        assert response.getStatusLine().getStatusCode() / 100 == 2 : "Status code returns is not ok!\n";
    }

    private void printHeader(HttpResponse response) {
        org.apache.http.Header[] headers = response.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            System.out.println(headers[i]);
        }
    }

    private void printBody(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseString);
    }
}
