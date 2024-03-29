package external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Connector {

    private final Logger logger = LoggerFactory.getLogger(Connector.class);

    private final int CONNECTION_TIMEOUT = 60000;
    private final int READ_TIMEOUT = 60000;

    private final String username, password;

    public Connector(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String post(Request request, String restPath) {
        logger.debug("HTTP POST: " + restPath);

        try {
            long start = System.currentTimeMillis();

            HttpURLConnection httpCon = createHttpURLConnection(request, restPath, "POST", true);

            readInput(request, httpCon);

            request.setResponseTime(System.currentTimeMillis() - start);

            return request.getResponseXml();
        } catch (IOException e) {
            request.setResponseCode(-1);
            System.out.println("IOException: " + e.getMessage());
        }
        return null;
    }

    public String put(Request request, String restPath) {
        logger.debug("HTTP PUT: " + restPath);

        try {
            long start = System.currentTimeMillis();

            HttpURLConnection httpCon = createHttpURLConnection(request, restPath, "PUT", true);

            readInput(request, httpCon);

            request.setResponseTime(System.currentTimeMillis() - start);

            return request.getResponseXml();
        } catch (IOException e) {
            request.setResponseCode(-1);
            System.out.println("IOException: " + e.getMessage());
        }
        return null;
    }

    public String get(Request request, String restPath) {
        logger.debug("HTTP GET: " + restPath);

        try {
            long start = System.currentTimeMillis();

            HttpURLConnection httpCon = createHttpURLConnection(request, restPath, "GET", false);

            readInput2(request, httpCon);

            request.setResponseTime(System.currentTimeMillis() - start);

            return request.getResponseXml();
        } catch (IOException e) {
            request.setResponseCode(-1);
            System.out.println("IOException: " + e.getMessage());
        }
        return null;
    }

    public String delete(Request request, String restPath) {
        logger.debug("HTTP DELETE: " + restPath);

        try {
            long start = System.currentTimeMillis();

            HttpURLConnection httpCon = createHttpURLConnection(request, restPath, "DELETE", false);

            readInput2(request, httpCon);

            request.setResponseTime(System.currentTimeMillis() - start);

            return request.getResponseXml();
        } catch (IOException e) {
            request.setResponseCode(-1);
            System.out.println("IOException: " + e.getMessage());
        }
        return null;
    }

    private HttpURLConnection createHttpURLConnection(Request request, String path, String method, boolean doOutput) throws IOException {
        URL url = new URL(request.getServer() + path);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

        if (username != null && password != null) {
            String login = username + ":" + password;
            String auth = "Basic " + new String(Base64.getEncoder().encode(login.getBytes()));
            httpCon.setRequestProperty("Authorization", auth);
        }

        httpCon.setRequestMethod(method);
        httpCon.setConnectTimeout(CONNECTION_TIMEOUT);
        httpCon.setReadTimeout(READ_TIMEOUT);

        if (doOutput) {
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty("accept-charset", "UTF-8");
            httpCon.setRequestProperty("content-type", "application/xml");

            writeOutput(request.getXml(), httpCon);
        }

        request.setResponseCode(httpCon.getResponseCode());
        request.setResponseMessage(httpCon.getResponseMessage());

        return httpCon;
    }

    private void writeOutput(String payload, HttpURLConnection httpCon) throws IOException {
        if (payload != null) {
            OutputStream os = httpCon.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            bw.write(payload);
            bw.flush();
            bw.close();
        }
    }

    private void readInput(Request request, HttpURLConnection httpCon) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream iStream = httpCon.getErrorStream() == null ? httpCon.getInputStream() : httpCon.getErrorStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(iStream, "UTF-8"));
        String brLine = br.readLine();

        while (brLine != null) {
            sb.append(brLine);
            brLine = br.readLine();
        }

        br.close();

        request.setResponseXml(sb.toString());
    }

    private void readInput2(Request request, HttpURLConnection httpCon) throws IOException {
        request.clearStrings();

        StringBuilder sb = new StringBuilder();
        InputStream iStream = httpCon.getErrorStream() == null ? httpCon.getInputStream() : httpCon.getErrorStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(iStream, "UTF-8"));
        String brLine = br.readLine();

        while (brLine != null) {
            sb.append(brLine);
            request.appendString(brLine);
            brLine = br.readLine();
        }

        br.close();

        request.setResponseXml(sb.toString());
    }
}
