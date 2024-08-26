package Modèle.ClassesMétier;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.HashMap;

public class Client extends Personne
{
    private String Username;
    private String Password;

    private static HashMap<String, String> clients = new HashMap<>();

    public Client() {
        super();
        Username = "User";
        Password = "abcd";
    }

    public Client(String Username, String Password) {

        this.Username = Username;
        this.Password = Password;
    }

    public Client(String Nom, String Prenom, String Username, String Password) {
        super(Nom, Prenom);
        this.Username = Username;
        this.Password = Password;
    }

    public static Client seConnecter(String username, String password)
    {
        loadUserData(); // Charger les données des utilisateurs depuis le fichier
        // Vérifier si le client existe déjà
        if (clients.containsKey(username))
        {
            String storedPassword = clients.get(username);
            // Vérifier le mot de passe
            if (storedPassword.equals(password)) {
                System.out.println("Connexion réussie pour " + username);
                return new Client(username, password);
            } else {
                System.out.println("Mot de passe incorrect pour " + username);
                return null;
            }
        } else {
            // Créer un nouveau client
            clients.put(username, password);
            saveUserData(); // Sauvegarder les données des utilisateurs dans le fichier
            System.out.println("Nouvel utilisateur créé : " + username);
            return new Client(username, password);
        }
    }

    private static void loadUserData()
    {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("users.properties")) {
            properties.load(fis);
            for (String username : properties.stringPropertyNames()) {
                clients.put(username, properties.getProperty(username));
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void saveUserData() {
        Properties properties = new Properties();
        for (String username : clients.keySet()) {
            properties.setProperty(username, clients.get(username));
        }
        try (FileOutputStream fos = new FileOutputStream("users.properties")) {
            properties.store(fos, "Utilisateurs enregistrés");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Getters and setters
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    // Méthode pour afficher les informations du client
    @Override
    public void Afficher() {
        super.Afficher();
        System.out.println("Username : " + Username);
        System.out.println("Password : " + Password);
    }

    // Méthode pour saisir les informations du client
    @Override
    public void Saisir() {
        Scanner scanner = new Scanner(System.in);
        super.Saisir();

        System.out.println("Entrez le nom d'utilisateur : ");
        this.Username = scanner.nextLine();

        System.out.println("Entrez le mot de passe : ");
        this.Password = scanner.nextLine();

    }

    // Surcharge de la méthode toString
    @Override
    public String toString() {
        return super.toString() +
                "\nUsername : " + Username +
                "\nPassword : " + Password;
    }

    // Méthode equals pour comparer deux clients
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Username.equals(client.Username) && Password.equals(client.Password);
    }



    public static void main(String[] args) {
        String Nom = "NULL";
        String Prenom = "NULL";
        String Username = "USER";
        String Password = "ABCD";

        Client client = new Client(Nom, Prenom, Username, Password);

        client.Saisir();

        client.Afficher();

        Client client2 = new Client();

        client2.Afficher();

        if (client == client2) {
            System.out.println("Ce sont les mêmes clients");
        } else {
            System.out.println("Ce sont des clients différents");
        }

        Client client3 = new Client("Toto", Prenom, Username, "1234");

        client3.Saisir();

        client3.Afficher();

        // Nouveaux tests
        System.out.println("Test de la méthode toString :");
        System.out.println(client.toString());
        System.out.println(client2.toString());
        System.out.println(client3.toString());

        System.out.println("Test de la méthode equals :");
        System.out.println("client.equals(client2) : " + client.equals(client2));
        System.out.println("client.equals(client3) : " + client.equals(client3));
    }
}
