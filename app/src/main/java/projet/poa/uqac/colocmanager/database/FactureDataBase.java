package projet.poa.uqac.colocmanager.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import projet.poa.uqac.colocmanager.Facture;
import projet.poa.uqac.colocmanager.Utilisateur;

/**
 * Created by Jimmy
 */
public class FactureDataBase extends SQLiteOpenHelper
{
    private ArrayList<Facture> listBills;
    public FactureDataBase(Context context)
    {
        super(context, "CollocManager.db", null, 1);
        SQLiteDatabase database = this.getWritableDatabase();
        listBills = new ArrayList<Facture>();
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS bills  ");
        db.execSQL("CREATE TABLE IF NOT EXISTS bills("+
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "date_facture string, "+
                        "titre_facture string, "+
                        "nom_magasin string, "+
                        "description string, "+
                        "beneficiaire string, " +
                        "cout_total real, "+
                        "facture_reglee INTEGER, "+
                        "FOREIGN KEY(beneficiaire) REFERENCES user(pseudo)" +
                ")");
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS facture");
        onCreate(db);
    }

    public void addFacture(Facture f)
    {
        int reglee = 0;

        for(int i=0 ; i<f.getListePersonneIntervenant().size(); i++)
        {
            this.getWritableDatabase().execSQL("INSERT INTO bills (date_facture, titre_facture, nom_magasin, description, beneficiaire, cout_total, facture_reglee VALUES ('" +
                    f.getDateAchat() + "','" +
                    f.getTitre() + "','" +
                    f.getMagasinAchat() + "','" +
                    f.getDescription() + "','" +
                    f.getListePersonneIntervenant().get(i).getPseudo() + "','" +
                    f.getCoutParPersonne() + "','" +
                    reglee + "')");

        }
        this.getWritableDatabase().close();
    }

    public ArrayList<Facture> getBills(ArrayList<Utilisateur> listUser)
    {
        listBills.clear();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor billsSaved = db.rawQuery("SELECT * FROM bills", null);

        String date;
        String titre;
        String magasin;
        String description;
        String pseudo;
        double cout;
        int reglee;
        boolean isRegle = true;

        for(billsSaved.moveToFirst(); !billsSaved.isAfterLast(); billsSaved.moveToNext())
        {
            date = billsSaved.getString(1);
            titre = billsSaved.getString(2);
            magasin = billsSaved.getString(3);
            description = billsSaved.getString(4);
            pseudo = billsSaved.getString(5);
            cout = billsSaved.getDouble(6);
            reglee = billsSaved.getInt(7);

            if (reglee ==0)
            {
                isRegle = false;
            }
            else



            for (int i=0; i<listUser.size(); i++)
            {
                if(listUser.get(i).getPseudo() == pseudo)
                {
                    Facture f = new Facture(date,titre,magasin,description,listUser.get(i), cout, isRegle);
                    listBills.add(f);
                }
            }
        }

        return listBills;
    }
}
