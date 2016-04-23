package projet.poa.uqac.colocmanager.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import projet.poa.uqac.colocmanager.MainActivity;
import projet.poa.uqac.colocmanager.R;
import projet.poa.uqac.colocmanager.Utilisateur;
import projet.poa.uqac.colocmanager.database.UserDataBase;

public class FactureActivity extends AppCompatActivity {


    private Calendar newCalendar;

    private EditText ed_Date;

    private EditText ed_Titre;

    private EditText ed_Magasin;

    private EditText ed_participant;

    private EditText ed_prixAchat;

    private EditText ed_Description;

    private SimpleDateFormat dateFormat;


    ArrayList<Utilisateur> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_facture);
        newCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA_FRENCH);

        ed_Date =(EditText) findViewById(R.id.ED_date);
        ed_Description = (EditText) findViewById(R.id.ED_description);
        ed_Magasin = (EditText) findViewById(R.id.ED_magasin);
        ed_participant = (EditText) findViewById(R.id.ED_participant);
        ed_Titre = (EditText) findViewById(R.id.ED_titref);
        ed_prixAchat = (EditText) findViewById(R.id.ED_prixachat);

        super.onCreate(savedInstanceState);

        listUser = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facture, menu);
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

    public void onClickAddDate(View v)
    {
        System.out.println("Clique pour ajouter une date");
        DatePickerDialog datedialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ed_Date.setText(dateFormat.format(newDate.getTime()));
                newCalendar.set(year, monthOfYear, dayOfMonth);
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datedialog.show();
    }

    public void onBackPressed()
    {
        Intent main = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(main);
    }

    public void onClickSaveBill(View v)
    {


    }

}
