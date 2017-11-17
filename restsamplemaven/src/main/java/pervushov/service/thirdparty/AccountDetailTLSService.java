package pervushov.service.thirdparty;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import pervushov.model.Account;

import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by a.pervushov on 17.11.2017.
 */
@Service
public class AccountDetailTLSService implements AccountDetailService {
    private static final String TARGET_HTTPS_SERVER = "tls-test.scnetservices.ru";
    private static final int    TARGET_HTTPS_PORT   = 9000;

    private SocketFactory sslSocketFactory;

    public AccountDetailTLSService() {
        sslSocketFactory = SSLSocketFactory.getDefault();
    }

    @Override
    public String getAccountDetailsById(Integer id) {

        Account account = new Account();
        account.setId(id);

        Gson gson = new Gson();

        try (Socket socket = sslSocketFactory.
                createSocket(TARGET_HTTPS_SERVER, TARGET_HTTPS_PORT);){

            Writer out = new OutputStreamWriter(
                    socket.getOutputStream(), "ISO-8859-1");

            out.write(gson.toJson(account));
            out.write("\r\n");
            out.flush();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), "ISO-8859-1"));
            String output = in.readLine();

            account = gson.fromJson(output, Account.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return account.getAccountDetails();
    }
}
