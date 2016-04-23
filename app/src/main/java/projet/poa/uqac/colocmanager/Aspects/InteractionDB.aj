package projet.poa.uqac.colocmanager.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import java.lang.reflect.Field;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;


import projet.poa.uqac.colocmanager.MainActivity;
import projet.poa.uqac.colocmanager.Activities.UtilisateurActivity;
import projet.poa.uqac.colocmanager.Activities.FactureActivity;
import projet.poa.uqac.colocmanager.database.*;
import projet.poa.uqac.colocmanager.Utilisateur;

public aspect InteractionDB {


    private UserDataBase userdb;
    private FactureDataBase billsDb;

    public pointcut callOnCreate() : execution(void *.onCreate(..));
    public pointcut callOnAddUser(Utilisateur u) : execution(void UtilisateurActivity.saveUserInDb(..)) && args(u);


    after (): callOnCreate()
            {
                AppCompatActivity activity = (AppCompatActivity) thisJoinPoint.getThis();
                userdb = new UserDataBase(activity);
                billsDb = new FactureDataBase(activity);
                //Utilisateur u = new Utilisateur("Arnaud" , "DENIZET", "arnaudden", 0);
                //userdb.addUser(u);
                //System.out.println(" aspect ajout utilisateur");
                ArrayList<Utilisateur> listUser = userdb.getUsers();
                for(int i =0; i<listUser.size(); i++)
                        {
                            System.out.println(listUser.get(i).getPrenom());
                        }
                String className = activity.getClass().toString();
                Field[] fields = activity.getClass().getFields();
                Field f;
                for(int i =0; i<fields.length; i++)
                        {
                            f = fields[i];
                            if (f.getName() == "listUser")
                            {
                                try{
                                    f.set(activity, listUser);
                                }
                                catch(Exception e) {
                                e.printStackTrace();
                                }
                            }
                        }
            }

    void around (Utilisateur u) : callOnAddUser(u)
    {
        System.out.println(" aspect ajout utilisateur");
        userdb.addUser(u);
        System.out.println(u.toString() + "a été ajouté dans la BDD");
    }


}