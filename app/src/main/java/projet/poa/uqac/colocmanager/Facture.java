package projet.poa.uqac.colocmanager;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Cette classe gère les factures rentrées par l'utilisateur
 * Created by Arnaud
 */
public class Facture {

    private String dateAchat;

    private String titre;

    private String magasinAchat;

    private String description;

    private Utilisateur utilisateurIntervenant;


    private ArrayList<Utilisateur> listePersonneIntervenant;


    private double coutTotal;

    private double coutParPersonne;

    private boolean factureReglee;

    private boolean isAcheteur;


    public Facture(String date,
                   String t, String magasin,
                   String descr, Utilisateur u,
                   double coutTot, boolean isReglee)
    {

        dateAchat = date;

        titre = t;

        magasinAchat = magasin;

        description = descr;

        utilisateurIntervenant = u;

        listePersonneIntervenant = new ArrayList<>();

        listePersonneIntervenant.add(u);

        coutTotal = coutTot;
        coutParPersonne = coutTot;

        factureReglee = isReglee;
    }

    public Facture(String date,
                   String t, String magasin,
                   String descr, ArrayList<Utilisateur> listPerson,
                   double coutTot, boolean isAch)
    {

        dateAchat = date;

        titre = t;

        magasinAchat = magasin;

        description = descr;

        listePersonneIntervenant = listPerson;

        coutTotal = coutTot;

        isAcheteur = isAch;

        if(isAch)
        {
            coutParPersonne = coutTot/(listePersonneIntervenant.size()+1);
        }
        else if (!isAch)
        {
            coutParPersonne = coutTot/listePersonneIntervenant.size();
        }

        for (int i =0; i<listePersonneIntervenant.size(); i++)
        {
            listePersonneIntervenant.get(i).setDette(coutParPersonne);
        }

        factureReglee = false;
    }

    public String getDateAchat() {
        return dateAchat;
    }

    public String getTitre() {
        return titre;
    }

    public String getMagasinAchat() {
        return magasinAchat;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Utilisateur> getListePersonneIntervenant() {
        return listePersonneIntervenant;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public double getCoutParPersonne() {
        return coutParPersonne;
    }

    public boolean isFactureReglee() {
        return factureReglee;
    }



    public Utilisateur getUtilisateurIntervenant() {
        return utilisateurIntervenant;
    }

    public String toString()
    {
        String str = "Facture datant du " + dateAchat + " acheté à " + magasinAchat +
                      " pour une valeur de " + coutParPersonne + " par personne. Il y a :";
        for(int i =0; i<listePersonneIntervenant.size();i++)
        {
            str = str + listePersonneIntervenant.get(i).getPseudo() + " ";
        }

        str = str + "La facture est réglée " + factureReglee;

        return str;
    }
}
