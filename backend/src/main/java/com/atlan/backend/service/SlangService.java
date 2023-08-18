package com.atlan.backend.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@Service
public class SlangService {
    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";

    public String findSlang(String fromLang, String toLang, String text) throws Exception {
        String jsonPayload = "{" +
                "\"fromLang\":\"" +
                fromLang +
                "\"," +
                "\"toLang\":\"" +
                toLang +
                "\"," +
                "\"text\":\"" +
                text +
                "\"" +
                "}";

        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonPayload.getBytes());
        os.flush();
        os.close();

        int statusCode = conn.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));
        String output;
        String outputText="";
        while ((output = br.readLine()) != null) {
            outputText=output;
        }
        conn.disconnect();
        return outputText;
    }
}
