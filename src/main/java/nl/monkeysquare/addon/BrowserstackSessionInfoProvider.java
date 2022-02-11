package nl.monkeysquare.addon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BrowserstackSessionInfoProvider {

    ObjectMapper mapper = new ObjectMapper();
    String deviceNameString = "";

    public String GetBrowserstackSessionInfo(String sessionID, String username, String password) throws IOException {

        String deviceNameString = "";
        try {
            URL url = new URL("https://api.browserstack.com/app-automate/sessions/"+sessionID+".json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", authHeaderValue);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                responseBody body = mapper.readValue(output, responseBody.class);
                deviceNameString = body.getautomation_session().getDevice();
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deviceNameString;
    }
}
