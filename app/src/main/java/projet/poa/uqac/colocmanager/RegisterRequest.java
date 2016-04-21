package projet.poa.uqac.colocmanager;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jimmy on 19/04/2016.
 */
public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://applicolloc.netne.net/Register.php";
    private Map<String, String> credentials;

    public RegisterRequest(String pseudo, String password, String prenom, String nom, Response.Listener<String> listener)
    {
        super( Method.POST, REGISTER_REQUEST_URL, listener, null);
        credentials = new HashMap<>();
        credentials.put( "pseudo", pseudo);
        credentials.put( "password", password);
        credentials.put( "prenom", prenom);
        credentials.put( "nom", nom);
        credentials.put( "dette", 0.0 + "");
    }


    public Map<String, String> getCredentials()
    {return credentials;
    }

}
