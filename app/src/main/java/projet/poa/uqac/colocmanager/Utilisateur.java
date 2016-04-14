package projet.poa.uqac.colocmanager;

/**
 * Classe qui gère les utilisateurs
 * Created by Arnaud
 */
public class Utilisateur {

    private String prenom;

    private String nom;

    private String pseudo;

    private double dette;

    public Utilisateur(String n, String p, String pseudo)
    {
        nom = n;
        prenom = p;
        this.pseudo = pseudo;
        dette = 0;
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


}
