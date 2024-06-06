package Modèle.CoucheAccèsDonnées;

import Modèle.ClassesMétier.*;
import Modèle.CoucheAccèsDonnées.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class CoucheAccèsDonnéesDAO implements CoucheAccèsDonnées
{
    private String filePath = "C:\\Users\\Loris\\Personnes\\Books.txt";
    private ArrayList<Livre> livres;

    private static int IdCourant = 1;

    public CoucheAccèsDonnéesDAO()
    {
        livres = new ArrayList<>();
    }

    @Override
    public String GenererISBN()
    {
        String ISBN = "";
        for (int i = 0; i < 6; i++)
        {
            ISBN += (int)(Math.random() * 10);
        }
        return ISBN;
    }
    @Override
    public boolean emprunterLivre(int id)
    {
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

    @Override
    public boolean rendreLivre(int id)
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

    public List<Livre> rechercherLivre(String motCle)
    {
        List<Livre> resultats = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Books.txt")))
        {
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

                    if (titre.toLowerCase().contains(motCle.toLowerCase()))
                    {
                        Livre livre = new Livre(id, titre, auteur, editeur, Annee, ISBN, quantite);
                        resultats.add(livre);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return resultats;
    }

    @Override
    public void AjouteLivre(String filePath, Livre livre)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true)))
        {
            writer.write(livre.getID()  + ", " +
                    livre.getTitre() + ", " +
                    livre.getAuteur() + ", " +
                    livre.getEditeur() + ", " +
                    livre.getAnnee() + ", " +
                    GenererISBN() + ", " +
                    livre.getNombreCopies());
            writer.newLine();
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }

    @Override
    public boolean supprimerLivre(int id, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".tmp"))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int bookID = Integer.parseInt(parts[0].trim());

                // Si l'ID du livre correspond à celui que nous voulons supprimer, nous le sautons
                if (bookID == id) {
                    found = true;
                    continue;
                }

                // Sinon, nous écrivons la ligne dans le nouveau fichier temporaire
                writer.write(line);
                writer.newLine();
            }

            if (!found) {
                System.out.println("Livre non trouvé avec l'ID : " + id);
                return false;
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture/écriture du fichier : " + e.getMessage());
            return false;
        }

        // Si le livre est trouvé et supprimé, nous remplaçons le fichier d'origine par le nouveau fichier temporaire
        try {
            java.nio.file.Files.move(java.nio.file.Paths.get(filePath + ".tmp"),
                    java.nio.file.Paths.get(filePath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression du fichier temporaire : " + e.getMessage());
            return false;
        }

        // Afficher un message dans la console pour le suivi
        System.out.println("Livre supprimé avec succès (ID : " + id + ")");
        return true;

    }

    @Override
    public boolean modifierLivre(int id, String nouveauTitre, String nouvelAuteur, String nouvelEditeur, int nouvelleAnnee, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".tmp"))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int bookID = Integer.parseInt(parts[0].trim());

                if (bookID == id) {
                    found = true;
                    // On modifie les informations du livre
                    parts[1] = nouveauTitre;
                    parts[2] = nouvelAuteur;
                    parts[3] = nouvelEditeur;
                    parts[4] = String.valueOf(nouvelleAnnee);

                    // On réécrit la ligne modifiée
                    line = String.join(", ", parts);
                }

                // On écrit la ligne (modifiée ou non) dans le fichier temporaire
                writer.write(line);
                writer.newLine();
            }

            if (!found) {
                System.out.println("Livre non trouvé avec l'ID : " + id);
                return false;
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture/écriture du fichier : " + e.getMessage());
            return false;
        }

        // On remplace le fichier original par le fichier temporaire
        try
        {
            Files.move(Paths.get(filePath + ".tmp"), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erreur lors du remplacement du fichier : " + e.getMessage());
            return false;
        }

        // Afficher un message dans la console pour le suivi
        System.out.println("Livre modifié avec succès (ID : " + id + ")");
        return true;
    }

    @Override
    public void affiche(String filePath)
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


}
