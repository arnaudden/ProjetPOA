package projet.poa.uqac.colocmanager.Activities;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

import projet.poa.uqac.colocmanager.MainActivity;
import projet.poa.uqac.colocmanager.R;
import projet.poa.uqac.colocmanager.RegisterRequest;
import projet.poa.uqac.colocmanager.Utilisateur;

public class UtilisateurActivity extends AppCompatActivity {


    private EditText _prenomET;

    private EditText _nomED;

    private EditText _pseudoED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur);
        _prenomET = (EditText) findViewById(R.id.TV_Prenom);
        _nomED = (EditText) findViewById(R.id.TV_Nom);
        _pseudoED = (EditText) findViewById(R.id.TV_Pseudo);
    }


    public void onBackPressed()
    {
        Intent main = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(main);
    }

    public void onClickEnregisterUser(View v)
    {
        Toast.makeText(this, "EnregistrerUSer", Toast.LENGTH_SHORT);
        final String pseudo = _pseudoED.getText().toString();
        final String password = "banana";
        final String prenom = _prenomET.getText().toString();
        final String nom = _nomED.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse( String response)
            {
                try {
                    JSONObject JSonsresponse = new JSONObject(response);
                    boolean success = JSonsresponse.getBoolean("success");
                    if( success)
                    {
                        Intent intent = new Intent( UtilisateurActivity.this, MainActivity.class);
                        UtilisateurActivity.this.startActivity(intent);
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UtilisateurActivity.this);
                        builder.setMessage("Register Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest regRequest = new RegisterRequest( pseudo, password, prenom, nom, responseListener);
        RequestQueue queue = Volley.newRequestQueue(UtilisateurActivity.this);
        queue.add(regRequest);
    }


    public void saveUser(View v)
    {

    }


}
