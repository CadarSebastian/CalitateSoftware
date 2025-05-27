

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class InfluxLogger {

    public static void logResult(String testCase, String status) {
        try {
            String data = String.format("test_results,test_case=%s,status=%s value=1", testCase, status);
            URL url = new URL("http://localhost:8086/write?db=test_metrics");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            con.setFixedLengthStreamingMode(out.length);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.connect();
            try (OutputStream os = con.getOutputStream()) {
                os.write(out);
            }
            System.out.println("Trimis: " + data + " -> " + con.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
