package projet.poa.uqac.colocmanager;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Cette classe gère les factures rentrées par l'utilisateur
 * Created by Arnaud
 */
public class Facture {

    private GregorianCalendar dateAchat;

    private String titre;

    private String magasinAchat;

    private String description;

    private Utilisateur payeur;

    private ArrayList<Utilisateur> listePersonneIntervenant;

    private double coutTotal;

    private double coutParPersonne;

    private boolean factureReglee;


    public Facture(GregorianCalendar date,
                   String t, String magasin,
                   String descr, ArrayList<Utilisateur> listPerson,
                   double coutTot)
    {

        dateAchat = date;

        titre = t;

        magasinAchat = magasin;

        description = descr;

        listePersonneIntervenant = listPerson;

        coutTotal = coutTot;

        coutParPersonne = coutTot/listePersonneIntervenant.size();

        factureReglee = false;
    }

    public GregorianCalendar getDateAchat() {
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


}
