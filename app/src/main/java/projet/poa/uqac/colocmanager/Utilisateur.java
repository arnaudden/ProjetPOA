package projet.poa.uqac.colocmanager;



/**
 * Classe qui gère les utilisateurs
 * Created by Arnaud
 */
public class Utilisateur {

    private String prenom;

    private String nom;

    private double dette;



    public Utilisateur(String n, String p, double d)
    {
        nom = n;
        prenom = p;
        dette = d;
    }

    public void setDette(double d)
    {
        dette = dette + d;
    }

    public double getDette()
    {
        return dette;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public String getNom() {
        return nom;
    }
}
