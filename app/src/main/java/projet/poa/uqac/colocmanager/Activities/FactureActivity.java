package projet.poa.uqac.colocmanager.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import projet.poa.uqac.colocmanager.Facture;
import projet.poa.uqac.colocmanager.R;
import projet.poa.uqac.colocmanager.Utilisateur;




public class FactureActivity extends AppCompatActivity {


    private Calendar newCalendar;

    private TextView ed_Date;

    private EditText ed_Titre;

    private EditText ed_Magasin;

    private TextView ed_participant;

    private EditText ed_prixAchat;

    private EditText ed_Description;

    private SimpleDateFormat dateFormat;

    private CheckBox cb_isAcheteur;

    ArrayList<Utilisateur> listUser;

    private ArrayList<Utilisateur> listUserSelect;

    private String usersSelec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_facture);
        newCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA_FRENCH);

        ed_Date =(TextView) findViewById(R.id.TV_date);
        ed_Description = (EditText) findViewById(R.id.ED_description);
        ed_Magasin = (EditText) findViewById(R.id.ED_magasin);
        ed_participant = (TextView) findViewById(R.id.ED_participant);
        ed_Titre = (EditText) findViewById(R.id.ED_titref);
        ed_prixAchat = (EditText) findViewById(R.id.ED_prixachat);
        cb_isAcheteur = (CheckBox) findViewById(R.id.CB_isAcheteur);
        super.onCreate(savedInstanceState);

        listUser = new ArrayList<>();
        listUserSelect = new ArrayList<>();
        usersSelec = "";
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


    public void onClickSelectionParticipant(View v)
    {
        final ArrayList<Integer> utilsateurSelectionne = new ArrayList<>();


        final String[] listPseudo = new String[listUser.size()];

        for (int i=0 ; i<listUser.size(); i++)
        {
            listPseudo[i]= listUser.get(i).getPseudo();
        }



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle("Selectionner des utilisateurs")
                      // Specify the list array
                      .setMultiChoiceItems(listPseudo, null,
                                    new DialogInterface.OnMultiChoiceClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which,
                                                            boolean isChecked) {
                                            if (isChecked) {
                                                // If the user checked the item,
                                                // add it to the selected items list
                                                utilsateurSelectionne.add(which);
                                            } else if (utilsateurSelectionne.contains(which)) {
                                                // Else, if the item is already in the list, remove it
                                                utilsateurSelectionne.remove(Integer.valueOf(which));
                                            }
                                        }
                                    })

                      // Set the action buttons
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int id) {
                              // User clicked OK, so pass the selectedItemsIndexList
                              // results to the host activity
                                for(int i=0; i<utilsateurSelectionne.size();i++)
                                {
                                    String pseudo = listPseudo[utilsateurSelectionne.get(i)];
                                    for(int j =0 ; j<listUser.size();j++)
                                    {
                                        if(listUser.get(j).getPseudo().equals(pseudo))
                                        {
                                            listUserSelect.add(listUser.get(j));
                                            usersSelec = usersSelec + " " + pseudo ;
                                        }
                                    }
                                }
                              ed_participant.setText(usersSelec);
                          }
                      })

                      .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int id) {

                          }
                      });
        builder.show();

    }

    public void onBackPressed()
    {
        Intent main = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(main);
    }

    public void onClickSaveBill(View v)
    {
        Toast.makeText(this, "EnregistrerFacture", Toast.LENGTH_SHORT).show();
        final String dateFacture = ed_Date.getText().toString();
        final String titre = ed_Titre.getText().toString();
        final String magasin = ed_Magasin.getText().toString();
        final String description = ed_Description.getText().toString();
        final double coutTotal = Double.parseDouble(ed_prixAchat.getText().toString());

        boolean isAcheteur = false;
        if(cb_isAcheteur.isChecked())
            isAcheteur = true;


        Facture f = new Facture(dateFacture, titre, magasin, description, listUserSelect, coutTotal, isAcheteur);

        saveFactureInDB(f);

        Intent main = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(main);


    }

    public void saveFactureInDB(Facture f)
    {

    }


}
