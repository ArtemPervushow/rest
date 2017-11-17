package pervushov.utils;

import com.google.gson.Gson;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import pervushov.model.Account;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by a.pervushov on 17.11.2017.
 */
public class SSL {
    /*public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        // Init the SSLContext with a TrustManager[] and SecureRandom()
        sc.init(null, null, new java.security.SecureRandom());

        //URL httpsURL = new URL("tls-test.scnetservices.ru:9000");
        URL httpsURL = new URL("https://tls-test.scnetservices.ru:9000");

        HttpsURLConnection con = (HttpsURLConnection) httpsURL.openConnection();
        con.setSSLSocketFactory(sc.getSocketFactory());

        con.connect();

        System.out.println(con.getResponseCode());
    }*/

    /*public static void main(String[] args) {
        HttpClient httpclient = new HttpClient();
        httpclient.getHostConfiguration().setProxy("myproxyhost", 8080);
        httpclient.getState().setProxyCredentials("my-proxy-realm", " myproxyhost",
                new UsernamePasswordCredentials("my-proxy-username", "my-proxy-password"));
        GetMethod httpget = new GetMethod("https://www.verisign.com/");
        try {
            httpclient.executeMethod(httpget);
            System.out.println(httpget.getStatusLine());
        } finally {
            httpget.releaseConnection();
        }
    }*/
    public static final String TARGET_HTTPS_SERVER = "tls-test.scnetservices.ru";
    public static final int    TARGET_HTTPS_PORT   = 9000;

    public static void main(String[] args) throws Exception {

        Socket socket = SSLSocketFactory.getDefault().
                createSocket(TARGET_HTTPS_SERVER, TARGET_HTTPS_PORT);
        try {
            Writer out = new OutputStreamWriter(
                    socket.getOutputStream(), "ISO-8859-1");

            String jsonText = " {\"id\":3}";
            Account account = new Account();
            account.setId(3);

            Gson gson = new Gson();
            String json = gson.toJson(account);

            System.out.println(json);

            out.write(json);
            out.write("\r\n");
            out.flush();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), "ISO-8859-1"));
            String line = in.readLine();
            System.out.println(line);
            /*while ((line = in.readLine()) != null) {
                System.out.println(line);
            }*/
        } finally {
            socket.close();
        }
    }

    /*public static void main(String[] args) throws Exception {

        Socket socket = SSLSocketFactory.getDefault().
                createSocket(TARGET_HTTPS_SERVER, TARGET_HTTPS_PORT);
        try {
            Writer out = new OutputStreamWriter(
                    socket.getOutputStream(), "ISO-8859-1");
            out.write("GET / HTTP/1.1\r\n");
            out.write("Host: " + TARGET_HTTPS_SERVER + ":" +
                    TARGET_HTTPS_PORT + "\r\n");
            out.write("Agent: SSL-TEST\r\n");
            out.write("\r\n");
            out.flush();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), "ISO-8859-1"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            socket.close();
        }
    }*/
}
