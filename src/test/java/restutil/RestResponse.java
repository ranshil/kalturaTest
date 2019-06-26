package restutil;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class RestResponse {

    public String body;
    protected int statusCode;
    protected HttpResponse response;

    RestResponse(HttpResponse response) throws IOException {
        if (response.getEntity() != null) {
            this.body = getStringFrom(response.getEntity().getContent());
        }

        this.statusCode = response.getStatusLine().getStatusCode();
        this.response = response;
    }

    private String getStringFrom(InputStream inputStream) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    List<Header> getAllHeaders() {
        List<Header> values = new LinkedList<>();
        for (Header h : response.getAllHeaders())
            values.add(h);
        return values;
    }

    boolean isRedirect() {
        return (statusCode / 100) == 3;
    }

    public boolean isSuccessful() {
        return (statusCode / 100) == 2;
    }

}
