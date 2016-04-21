package projet.poa.uqac.colocmanager.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import projet.poa.uqac.colocmanager.Utilisateur;
import projet.poa.uqac.colocmanager.Facture;

import java.util.ArrayList;

public class UserDataBase extends SQLiteOpenHelper{

    private ArrayList<Utilisateur> listUsers;

    public UserDataBase(Context context)
    {
        super(context, "CollocManager.db", null, 1);
        SQLiteDatabase database = this.getWritableDatabase();
        listUsers = new ArrayList<Utilisateur>();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE IF NOT EXISTS user("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "prenom STRING, "+
                "nom STRING, "+
                "dette REAL"+
                ")");
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}