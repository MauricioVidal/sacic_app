package semanaacademica.sacic.webservice.http;


import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Mauricio R. Vidal on 19/05/2016.
 */
public interface Http {
    String USER_AGENT = "Mozilla/5.0";
    String solicitar() throws IOException;

}
