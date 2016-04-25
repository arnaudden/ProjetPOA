package projet.poa.uqac.colocmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import projet.poa.uqac.colocmanager.R;
import projet.poa.uqac.colocmanager.Utilisateur;

public class UtilisateurActivity extends AppCompatActivity {


    private EditText _prenomET;

    private EditText _nomED;

    private EditText _pseudoED;
    ArrayList<Utilisateur> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur);
        _prenomET = (EditText) findViewById(R.id.TV_Prenom);
        _nomED = (EditText) findViewById(R.id.TV_Nom);
        _pseudoED = (EditText) findViewById(R.id.TV_Pseudo);
        listUser = new ArrayList<Utilisateur>();
    }


    public void onBackPressed()
    {
        Intent main = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(main);
    }

    public void onClickEnregisterUser(View v)
    {

        final String prenom = _prenomET.getText().toString();
        final String pseudo = _pseudoED.getText().toString();
        final String nom = _nomED.getText().toString();
        double d = 0;
        Utilisateur u = new Utilisateur(prenom, nom, pseudo, d);
        boolean isSave = saveUserInDb(u);
        if(isSave)
        {
            Toast.makeText(this, "Utilisateur enregistré :  " + u.toString(), Toast.LENGTH_SHORT).show();
            Intent main = new Intent(this, MainActivity.class);
            this.finish();
            startActivity(main);
        }
        else if (!isSave)
        {
            Toast.makeText(this, "Cette utilisateur existe déjà dans la BDD", Toast.LENGTH_SHORT).show();
        }




    }

    public boolean saveUserInDb(Utilisateur u)
    {
        return false;
    }


}
