package projet.poa.uqac.colocmanager.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import projet.poa.uqac.colocmanager.Utilisateur;

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
                "prenom string, "+
                "nom string, "+
                "dette REAL"+
                ")");
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void addUser(Utilisateur u)
    {
        this.getWritableDatabase().execSQL("INSERT INTO user (prenom, nom, dette) VALUES ('" +
                u.getPrenom() + "','" +
                u.getNom()+ "','" +
                u.getDette() +  "')");
        this.getWritableDatabase().close();

    }

    public ArrayList<Utilisateur> getUsers()

    {

        listUsers.clear();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor usersSaved = db.rawQuery("SELECT * FROM user", null);

        String prenom;
        String nom;
        double dette;

        for(usersSaved.moveToFirst(); !usersSaved.isAfterLast(); usersSaved.moveToNext())
        {
            prenom = usersSaved.getString(1);
            nom = usersSaved.getString(2);
            dette = usersSaved.getDouble(3);
            Utilisateur u = new Utilisateur(prenom,nom,dette);
            listUsers.add(u);
        }
        return listUsers;
    }


}