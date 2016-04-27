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




    public Utilisateur(String n, String p, String pseudo, double d)
    {
        nom = n;
        prenom = p;
        this.pseudo = pseudo;
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

    public String getPseudo() {
        return pseudo;
    }

    public String toString()
    {
        String str = "Prénom : " + prenom + " nom : " + nom + " pseudo : " + pseudo +" a une dette de " + dette + " $";
        return str;
    }
}
