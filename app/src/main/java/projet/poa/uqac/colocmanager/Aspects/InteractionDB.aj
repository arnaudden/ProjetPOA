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


import projet.poa.uqac.colocmanager.Activities.MainActivity;
import projet.poa.uqac.colocmanager.Activities.UtilisateurActivity;
import projet.poa.uqac.colocmanager.Activities.FactureActivity;
import projet.poa.uqac.colocmanager.Activities.FactureAdapter;
import projet.poa.uqac.colocmanager.database.*;
import projet.poa.uqac.colocmanager.Utilisateur;
import projet.poa.uqac.colocmanager.Facture;

public aspect InteractionDB {


    private UserDataBase userdb;
    private FactureDataBase billsDb;
    private ArrayList<Utilisateur> listUser;
    private ArrayList<Facture> listBills;

    public pointcut callOnCreate() : execution(void MainActivity.onCreate(..)) || execution(void UtilisateurActivity.onCreate(..)) || execution(void FactureActivity.onCreate(..));
    public pointcut callOnAddUser(Utilisateur u) : execution(boolean UtilisateurActivity.saveUserInDb(..)) && args(u);
    public pointcut callOnSaveBill(Facture f) : execution(void FactureActivity.saveFactureInDB(..)) && args(f) ;
    public pointcut callConstructorFA() : execution(FactureAdapter.new(..));
    public pointcut callUpdateBillAndUser(Facture f) : execution(void MainActivity.updateBillAndUserInDb(..)) && args(f);

    before() : callOnCreate()
    {
        AppCompatActivity activity = (AppCompatActivity) thisJoinPoint.getThis();
        userdb = new UserDataBase(activity);
        billsDb = new FactureDataBase(activity);
        listUser = userdb.getUsers();
        listBills = billsDb.getBills(listUser);

    }

    after (): callOnCreate()
            {
                AppCompatActivity activity = (AppCompatActivity) thisJoinPoint.getThis();
                userdb = new UserDataBase(activity);
                billsDb = new FactureDataBase(activity);
                listUser = userdb.getUsers();
                listBills = billsDb.getBills(listUser);
                for(int i =0; i<listUser.size(); i++)
                        {
                            System.out.println(listUser.get(i).toString());
                        }
                System.out.println(listBills.size());
                for(int i =0; i<listBills.size(); i++)
                        {
                            System.out.println(listBills.get(i).toString());
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

                            if(f.getName() == "listBills")
                            {
                                try{
                                    f.set(activity, listBills);
                                }
                                catch(Exception e) {
                                e.printStackTrace();
                                }

                            }
                        }
            }

    boolean around (Utilisateur u) : callOnAddUser(u)
    {
        System.out.println(" aspect ajout utilisateur");
        if(isUsernameAvailable(u))
        {
            userdb.addUser(u);
            System.out.println(u.toString() + "a été ajouté dans la BDD");
            return true;

        }
        return false;

    }



    boolean isUsernameAvailable(Utilisateur u)
    {

        System.out.println("Aspect IsUsernameAvailable " + listUser.size());
        boolean isAvailable = true;
        for( int i = 0; i < listUser.size(); i++)
        {
            System.out.println(listUser.get(i).getPseudo() == u.getPseudo());
            if(listUser.get(i).getPseudo().equals(u.getPseudo()))
            {
                isAvailable = false;
            }

        }
        return isAvailable;
    }

    void around(Facture f): callOnSaveBill(f)
    {
        billsDb.addFacture(f);
        for (int i =0; i < f.getListePersonneIntervenant().size() ; i++)
        {
            Utilisateur u = f.getListePersonneIntervenant().get(i);
            userdb.updateDette(u);
        }

    }

    void around(Facture facture) : callUpdateBillAndUser(facture)
    {
        System.out.println("In Aspect" + facture.toString());
        billsDb.updateBill(facture);
        listBills = billsDb.getBills(listUser);
        AppCompatActivity activity = (AppCompatActivity) thisJoinPoint.getThis();
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

                    if(f.getName() == "listBills")
                    {
                        try{
                            f.set(activity, listBills);
                        }
                        catch(Exception e) {
                        e.printStackTrace();
                        }

                    }
                }

    }

    after() : callConstructorFA()
    {
        FactureAdapter activity = (FactureAdapter) thisJoinPoint.getThis();
        Field[] fields = activity.getClass().getFields();
        Field f;
        for(int i =0; i<fields.length; i++)
                {
                    f = fields[i];
                    if(f.getName() == "listBills")
                    {
                        System.out.println(f.getName().toString());
                        try{
                            f.set(activity, listBills);
                        }
                        catch(Exception e) {
                        e.printStackTrace();
                        }
                    }
                }
    }



}