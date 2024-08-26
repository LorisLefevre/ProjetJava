package Modèle.ClassesMétier;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Livre implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int ID;
    private String Titre;
    private String Auteur;
    private String Editeur;
    private int Annee;
    private String ISBN;
    private int NombreCopies;

    private String filePath = "C:\\Users\\Loris\\Personnes\\Books.txt";

    private boolean emprunter;

    // Les getters
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

    public boolean getEmprunter()
    {
        return emprunter;
    }

    // Les setters
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

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public void setNombreCopies(int NombreCopies)
    {
        this.NombreCopies = NombreCopies;
    }

    public void setEmprunter(boolean emprunter)
    {
        this.emprunter = emprunter;
    }

    // Les autres méthodes

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

    public Livre(int ID,String Titre, String Auteur, String Editeur, int Annee, String ISBN, int NombreCopies)
    {
        this.ID = ID;
        this.Titre = Titre;
        this.Auteur = Auteur;
        this.Editeur = Editeur;
        this.Annee = Annee;
        this.ISBN = ISBN;
        this.NombreCopies = NombreCopies;
    }

    public String toString()
    {
        return "Livre{" +
                "ID=" + ID +
                ", Titre='" + Titre + '\'' +
                ", Auteur='" + Auteur + '\'' +
                ", Editeur='" + Editeur + '\'' +
                ", Annee=" + Annee +
                ", ISBN='" + ISBN + '\'' +
                ", NombreCopies=" + NombreCopies +
                '}';
    }

    public String GenererISBN()
    {
        String ISBN = "";
        for (int i = 0; i < 6; i++)
        {
            ISBN += (int)(Math.random() * 10);
        }
        return ISBN;
    }



    public void Afficher()
    {
        System.out.println("ID : " + ID);
        System.out.println("Titre : " + Titre);
        System.out.println("Auteur : " + Auteur);
        System.out.println("Editeur : " + Editeur);
        System.out.println("Annee : " + Annee);
        System.out.println("ISBN : " + ISBN);
        System.out.println("Nombre de copies : " + NombreCopies);
    }

    public void Saisir()
    {
        System.out.println("Entrez l'ID : ");
        Scanner scanner = new Scanner(System.in);
        this.ID = scanner.nextInt();
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

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof Livre)) return false;
        Livre livre = (Livre)o;
        return this.Auteur.equals(livre.Auteur) && this.Titre.equals(livre.Titre) && this.Editeur.equals(livre.Editeur) && this.Annee == livre.Annee;
    }
    public static void affiche(String filePath)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        /*Livre livre = new Livre();
        livre.Saisir();
        livre.Afficher();

        // Test du constructeur par défaut
        Livre livre2 = new Livre();

        // Test du constructeur d'initialisation
        Livre livre3 = new Livre(1, "Le livre", "L'auteur", "L'editeur", 2022, "123456", 5);
        livre3.Afficher();

        // Tests de saisie et d'affichage
        Livre livre4 = new Livre();
        livre4.Saisir();
        livre4.Afficher();

        Livre livre5 = new Livre();
        livre5.Saisir();
        livre5.Afficher();

        // Test de toString
        System.out.println(livre3.toString());

        // Test de equals
        System.out.println(livre3.equals(livre5));*/

        Livre livre6 = new Livre();
        livre6.Saisir();
        livre6.Afficher();

        // Ajout de livres dans le fichier
        String filePath = "C:\\Users\\Loris\\Personnes\\Books.txt";
        /*livre3.AjouteLivre(filePath);
        livre4.AjouteLivre(filePath);
        livre5.AjouteLivre(filePath);
        livre6.AjouteLivre(filePath);
        supprimerLivre(7, filePath);
        modifierLivre(4, "Livre Modifié", "Nouvel Auteur", "Nouvel Editeur", 2024, filePath);*/
        affiche(filePath);
    }
}

