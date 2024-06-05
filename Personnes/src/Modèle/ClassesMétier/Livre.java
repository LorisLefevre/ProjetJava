package Modèle.ClassesMétier;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Livre
{
    private int ID;
    private static int nextID = 1;
    private String Titre;
    private String Auteur;
    private String Editeur;
    private int Annee;
    private String ISBN;
    private int NombreCopies;

    private String filePath = "C:\\Users\\Loris\\Personnes\\Books.txt";

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

    // Les autres méthodes

    public Livre()
    {
        IncrementeID();
        this.Titre = "";
        this.Auteur = "";
        this.Editeur = "";
        this.Annee = 0;
        this.ISBN = "";
        this.NombreCopies = 0;
    }

    public Livre(int ID,String Titre, String Auteur, String Editeur, int Annee, String ISBN, int NombreCopies)
    {
        IncrementeID();
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

    public int IncrementeID()
    {
        nextID++;
        this.ID = nextID;

        return nextID;
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
        Scanner scanner = new Scanner(System.in);
        this.ID = IncrementeID();
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

    public void AjouteLivre(String filePath)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true)))
        {
            writer.write(this.ID + IncrementeID() + ", " +
                    this.Titre + ", " +
                    this.Auteur + ", " +
                    this.Editeur + ", " +
                    this.Annee + ", " +
                    this.ISBN + ", " +
                    this.NombreCopies);
            writer.newLine();
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
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

    public static Livre getLivreById(int id)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Loris\\Personnes\\Books.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                int livreId = Integer.parseInt(parts[0]);
                if (livreId == id) {
                    String titre = parts[1];
                    String auteur = parts[2];
                    String editeur = parts[3];
                    int Annee = Integer.parseInt(parts[4]);
                    String ISBN = parts[5];
                    int copies = Integer.parseInt(parts[6]);
                    return new Livre(livreId, titre, auteur, editeur, Annee, ISBN, copies);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateCopiesInBooksFile(Livre livre)
    {
        File inputFile = new File("C:\\Users\\Loris\\Personnes\\Books.txt");
        File tempFile = new File("C:\\Users\\Loris\\Personnes\\Books_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)))
        {

            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if (id == livre.getID())
                {
                    writer.write(livre.getID() + "," + livre.getTitre() + "," + livre.getAuteur() + "," + livre.getEditeur() + "," + livre.getAnnee() + "," + livre.getISBN() + "," + livre.getNombreCopies());
                    writer.newLine();
                }
                else
                {
                    writer.write(line);
                    writer.newLine();
                }
            }
            inputFile.delete();
            tempFile.renameTo(inputFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean emprunterLivre(int id) {
        String booksFilePath = "C:\\Users\\Loris\\Personnes\\Books.txt";
        String lendBooksFilePath = "C:\\Users\\Loris\\Personnes\\LendBooks.txt";

        try (BufferedReader booksReader = new BufferedReader(new FileReader(booksFilePath));
             BufferedWriter lendWriter = new BufferedWriter(new FileWriter(lendBooksFilePath, true))) {

            String line;
            boolean found = false;

            while ((line = booksReader.readLine()) != null) {
                String[] parts = line.split(", ");
                int bookID = Integer.parseInt(parts[0].trim());

                if (bookID == id) {
                    found = true;
                    lendWriter.write(line + ", 1"); // Ajouter le nombre d'emprunts (initialisé à 1)
                    lendWriter.newLine();

                    // Afficher un message dans la console pour le suivi
                    System.out.println("Livre emprunté avec succès : " + line);
                    break; // Arrêter la boucle car le livre a été trouvé
                }
            }

            // Si le livre avec l'ID donné n'est pas trouvé dans le fichier
            if (!found) {
                System.out.println("Livre non trouvé avec l'ID : " + id);
                return false;
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture/écriture du fichier : " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean rendreLivre(int id)
    {
        String lendBooksFilePath = "C:\\Users\\Loris\\Personnes\\LendBooks.txt";
        String tempLendBooksFilePath = "C:\\Users\\Loris\\Personnes\\TempLendBooks.txt";

        try (BufferedReader lendReader = new BufferedReader(new FileReader(lendBooksFilePath));
             BufferedWriter tempLendWriter = new BufferedWriter(new FileWriter(tempLendBooksFilePath))) {

            String line;
            boolean found = false;

            while ((line = lendReader.readLine()) != null) {
                String[] parts = line.split(", ");
                int bookID = Integer.parseInt(parts[0].trim());

                if (bookID == id) {
                    found = true;
                    continue; // Ignorer cette ligne car c'est celle que nous voulons supprimer
                }

                tempLendWriter.write(line);
                tempLendWriter.newLine();
            }

            // Si le livre avec l'ID donné n'est pas trouvé dans le fichier
            if (!found) {
                System.out.println("Livre non trouvé avec l'ID : " + id);
                return false;
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture/écriture du fichier : " + e.getMessage());
            return false;
        }

        // Remplacer le fichier d'origine par le fichier temporaire
        try {
            java.nio.file.Files.move(java.nio.file.Paths.get(tempLendBooksFilePath),
                    java.nio.file.Paths.get(lendBooksFilePath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression du fichier temporaire : " + e.getMessage());
            return false;
        }

        // Afficher un message dans la console pour le suivi
        System.out.println("Livre rendu avec succès (ID : " + id + ")");

        return true;
    }

    public static List<Livre> rechercherLivre(String motCle)
    {
        List<Livre> resultats = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length >= 5) {
                    int id = Integer.parseInt(data[0]);
                    String titre = data[1];
                    String auteur = data[2];
                    String editeur = data[3];
                    int Annee = Integer.parseInt(data[4]);
                    String ISBN = data[5];
                    int quantite = Integer.parseInt(data[6]);

                    if (titre.toLowerCase().contains(motCle.toLowerCase())) {
                        Livre livre = new Livre(id, titre, auteur, editeur, Annee, ISBN, quantite);
                        resultats.add(livre);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultats;
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
        livre5.AjouteLivre(filePath);*/
        livre6.AjouteLivre(filePath);

        affiche(filePath);
    }
}

