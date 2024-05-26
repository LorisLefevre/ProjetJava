package Modèle.ClassesMétier;

import java.util.Scanner;

public class Livre
{
    private int ID;
    private String Titre;
    private String Auteur;
    private String Editeur;
    private int Annee;
    private String ISBN;
    private int NombreCopies;

    //Les getters

    public int getID()
    {
        return ID;
    }

    public String getTitre()
    {
        return Titre;
    }

    public String getAuteur()
    {
        return Auteur;
    }

    public String getEditeur()
    {
        return Editeur;

    }

    public int getAnnee()
    {
        return Annee;
    }

    public String getISBN()
    {
        return ISBN;

    }

    public int getNombreCopies()
    {
        return NombreCopies;

    }

    //Les setters

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public void setTitre(String Titre)
    {
        this.Titre = Titre;
    }


    public void setAuteur(String Auteur)
    {
        this.Auteur = Auteur;

    }

    public void setEditeur(String Editeur)
    {
        this.Editeur = Editeur;
    }

    public void setAnnee(int Annee)
    {
        this.Annee = Annee;
    }

    //l'id est auto-incrémenté de 1


    public void setID()
    {

    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public void setNombreCopies(int NombreCopies)
    {
        this.NombreCopies = NombreCopies;
    }

    //Les autres methodes

    //J'aimerai un constructeur par défaut et un constructeur d'initialisation

    public Livre()
    {
        this.ID = 0;
        this.Titre = "";
        this.Auteur = "";
        this.Editeur = "";
        this.Annee = 0;
        this.ISBN = "";
        this.NombreCopies = 0;
    }

    public Livre(int ID, String Titre, String Auteur, String Editeur, int Annee, String ISBN, int NombreCopies)
    {
        this.ID = ID;
        this.Titre = Titre;
        this.Auteur = Auteur;
        this.Editeur = Editeur;
        this.Annee = Annee;
        this.ISBN = ISBN;
        this.NombreCopies = NombreCopies;
    }
    //J'aimerai aussi un toString

    public String toString()
    {
        return "Modèle.CoucheAccèsDonnées.ClassesMétier.Livre{" +
                "ID=" + ID +
                ", Titre='" + Titre + '\'' +
                ", Modèle.CoucheAccèsDonnées.ClassesMétier.Auteur='" + Auteur + '\'' +
                ", Modèle.CoucheAccèsDonnées.ClassesMétier.Editeur='" + Editeur + '\'' +
                ", Annee=" + Annee +
                ", ISBN='" + ISBN + '\'' +
                ", NombreCopies=" + NombreCopies +
                '}';
    }

    //Méthode pour générer aléatoirement un ISBN à 6 chiffres

    public String GenererISBN()
    {
        String ISBN = "";
        for(int i = 0; i < 6; i++)
        {
            ISBN += (int)(Math.random() * 10);
        }
        return ISBN;
    }

    public void Afficher()
    {
        System.out.println("ID : " + ID);
        System.out.println("Titre : " + Titre);
        System.out.println("Modèle.CoucheAccèsDonnées.ClassesMétier.Auteur : " + Auteur);
        System.out.println("Modèle.CoucheAccèsDonnées.ClassesMétier.Editeur : " + Editeur);
        System.out.println("Annee : " + Annee);
        System.out.println("ISBN : " + ISBN);
        System.out.println("Nombre de copies : " + NombreCopies);
    }

    //l'isbn ne doit pas être saisi


    public void Saisir()
    {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Entrez le titre : ");
        this.Titre = scanner.nextLine();
        System.out.println("Entrez l'auteur : ");
        this.Auteur = scanner.nextLine();
        System.out.println("Entrez l'editeur : ");
        this.Editeur = scanner.nextLine();
        System.out.println("Entrez l'annee : ");
        this.Annee = scanner.nextInt();
        scanner.nextLine();
        this.ISBN = GenererISBN();
        System.out.println("Entrez le nombre de copies : ");
        this.NombreCopies = scanner.nextInt();
        scanner.nextLine();
    }

    //Fais-moi une méthode equals qui retourne true si le nom de l'auteur, le titre, l'éditeur et l'annee sont les mêmes

    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(!(o instanceof Livre)) return false;
        Livre livre = (Livre)o;
        return this.Auteur.equals(livre.Auteur) && this.Titre.equals(livre.Titre) && this.Editeur.equals(livre.Editeur) && this.Annee == livre.Annee;
    }

    public static void main(String[] args)
    {
        Livre livre = new Livre();
        livre.Saisir();
        livre.Afficher();

        //fais -moi un test de constructeur par défaut

        Livre livre2 = new Livre();

        //fais-moi un test de constructeur d'initialisation

        Livre livre3 = new Livre(1, "Le livre", "L'auteur", "L'editeur", 2022, "123456", 5);
        livre3.Afficher();

        //2 contructeurs par défaut à saisir et afficher + test du equals

        Livre livre4 = new Livre();
        livre4.Saisir();
        livre4.Afficher();

        Livre livre5 = new Livre();
        livre5.Saisir();
        livre5.Afficher();

        //fais-moi un test de toString

        System.out.println(livre3.toString());

        //fais-moi un test de equals

        System.out.println(livre3.equals(livre5));

    }
}

