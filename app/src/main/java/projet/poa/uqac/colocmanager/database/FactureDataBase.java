package projet.poa.uqac.colocmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import projet.poa.uqac.colocmanager.Facture;

/**
 * Created by Jimmy on 14/04/2016.
 */
public class FactureDataBase extends SQLiteOpenHelper
{
    public FactureDataBase(Context context)
    {
        super(context, "CollocManager.db", null, 1);
        SQLiteDatabase database = this.getWritableDatabase();
        listBills = new arrayLList<Facture>();
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS facture");
        db.execSQL("CREATE TABLE IF NOT EXISTS bills("+
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "date_facture, NUMERIC, "+
                        "titre_facture, TEXT, "+
                        "nom_magasin TEXT, "+
                        "description TEXT, "+
                        "acheteur, "+
                ")");
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}
