package projet.poa.uqac.colocmanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import projet.poa.uqac.colocmanager.Activities.*;
import projet.poa.uqac.colocmanager.Facture;
import projet.poa.uqac.colocmanager.R;
import projet.poa.uqac.colocmanager.Utilisateur;
import projet.poa.uqac.colocmanager.database.FactureDataBase;
import projet.poa.uqac.colocmanager.database.UserDataBase;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_1;
    private FloatingActionButton fab_2;
    private FloatingActionButton mainFab;
    //Animations
    Animation show_fab;
    Animation hide_fab_facture;
    Animation hide_fab_utilisateur;

    ArrayList<Utilisateur> listUser;
    ArrayList<Facture> listFacture;

    private UserDataBase userdb;
    private FactureDataBase billsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        show_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.show_fab);
        hide_fab_utilisateur = AnimationUtils.loadAnimation(getApplication(), R.anim.hide_fab_utilisateur);
        hide_fab_facture = AnimationUtils.loadAnimation(getApplication(), R.anim.hide_fab_facture);
        //userdb = new UserDataBase(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mainFab = (FloatingActionButton) findViewById(R.id.mainFab);
        fab_1 = (FloatingActionButton) findViewById(R.id.fab_ajouterUtilisateur);
        fab_2 = (FloatingActionButton) findViewById(R.id.fab_ajouterFacture);

        listUser = new ArrayList<Utilisateur>();
        listFacture = new ArrayList<Facture>();
    }

    public void onShowFab(View v)
    {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab_1.getLayoutParams();
        layoutParams.rightMargin += (int) (fab_1.getWidth() * 0.25);
        layoutParams.bottomMargin += (int) (fab_1.getHeight() * 3.4);
        fab_1.setLayoutParams(layoutParams);
        fab_1.startAnimation(show_fab);
        fab_1.setClickable(true);

        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab_2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fab_2.getWidth() * 0.25);
        layoutParams2.bottomMargin += (int) (fab_2.getHeight() * 1.7);
        fab_2.setLayoutParams(layoutParams2);
        fab_2.startAnimation(show_fab);
        fab_2.setClickable(true);

        mainFab.setClickable(false);

        for(int i =0; i<listUser.size(); i++)
        {
            System.out.println("ListUser de la classe MainActivity : " + listUser.get(i).toString());
        }
    }

    public void onClickMainScreen(View v)
    {
        if(!mainFab.isClickable())
        {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab_1.getLayoutParams();
            layoutParams.rightMargin -= (int) (fab_1.getWidth() * 0.25);
            layoutParams.bottomMargin -= (int) (fab_1.getHeight() * 3.4);
            fab_1.setLayoutParams(layoutParams);
            fab_1.startAnimation(hide_fab_utilisateur);
            fab_1.setClickable(false);

            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab_2.getLayoutParams();
            layoutParams2.rightMargin -= (int) (fab_2.getWidth() * 0.25);
            layoutParams2.bottomMargin -= (int) (fab_2.getHeight() * 1.7);
            fab_2.setLayoutParams(layoutParams2);
            fab_2.startAnimation(hide_fab_facture);
            fab_2.setClickable(false);

            mainFab.setClickable(true);
        }
    }

    public void onClickAddUser(View v)
    {
        Toast.makeText(this,"Clique sur ajouter Utilisateur",Toast.LENGTH_SHORT).show();
        Intent ajouterUtilisateur = new Intent(this, UtilisateurActivity.class);
        startActivity(ajouterUtilisateur);
        System.out.println(" classe Clique sur addUser");
    }

    public void onClickAddFacture(View v)
    {
        Toast.makeText(this,"Clique sur ajouter facture", Toast.LENGTH_SHORT).show();
        Intent ajouterFacture = new Intent(this, FactureActivity.class);
        startActivity(ajouterFacture);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
