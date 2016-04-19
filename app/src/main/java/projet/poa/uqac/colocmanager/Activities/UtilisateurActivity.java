package projet.poa.uqac.colocmanager.Activities;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import projet.poa.uqac.colocmanager.MainActivity;
import projet.poa.uqac.colocmanager.R;

public class UtilisateurActivity extends AppCompatActivity {

    private Button _enregistrerbutton;

    private EditText _prenomET;

    private EditText _nomED;

    private EditText _pseudoED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur);
    }


    public void onBackPressed()
    {
        Intent main = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(main);
    }

    public void saveUser(View v)
    {

    }


}
