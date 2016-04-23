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

    public pointcut callOnCreate() : execution(void *.onCreate(..));

    after (): callOnCreate()
            {
                AppCompatActivity activity = (AppCompatActivity) thisJoinPoint.getThis();
                userdb = new UserDataBase(activity);
                Utilisateur u = new Utilisateur("Arnaud" , "DENIZET", 0);
                userdb.addUser(u);
                System.out.println(" aspect ajout utilisateur");
                ArrayList<Utilisateur> listUser = userdb.getUsers();
                for(int i =0; i<listUser.size(); i++)
                        {
                            System.out.println(listUser.get(i).getPrenom());
                        }
                String className = activity.getClass().toString();
                Field[] fields = activity.getClass().getFields();
                Field f;


            }

}