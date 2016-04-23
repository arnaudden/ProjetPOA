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
        //onCreate(database);
        listUsers = new ArrayList<Utilisateur>();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("CREATE TABLE IF NOT EXISTS user("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "prenom string, "+
                "nom string, "+
                "pseudo string, " +
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
        this.getWritableDatabase().execSQL("INSERT INTO user (prenom, nom, pseudo, dette) VALUES ('" +
                u.getPrenom() + "','" +
                u.getNom()+ "','" +
                u.getPseudo() + "','" +
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
        String pseudo;
        double dette;


        for(usersSaved.moveToFirst(); !usersSaved.isAfterLast(); usersSaved.moveToNext())
        {
            prenom = usersSaved.getString(1);
            nom = usersSaved.getString(2);
            pseudo = usersSaved.getString(3);
            dette = usersSaved.getDouble(4);
            Utilisateur u = new Utilisateur(prenom,nom, pseudo, dette);
            listUsers.add(u);
        }
        return listUsers;
    }


}