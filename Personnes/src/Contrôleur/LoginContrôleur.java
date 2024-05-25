package Contrôleur;

import ClassesMétier.*;
import InterfacesGraphiques.LibraryClient;
import InterfacesGraphiques.LibraryManager;
import InterfacesGraphiques.LoginWindow;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public final class LoginContrôleur
{
    private LibraryClient libraryClient;

    private LibraryManager libraryManager;

    private Bibliothecaire bibliothecaire;

    private Livre livre;
    private Editeur editeur;
    private Auteur auteur;
    private Client client;

    private LoginWindow loginWindow;

    public LoginContrôleur(LoginWindow loginWindow, LibraryManager libraryManager, LibraryClient libraryClient)
    {
        this.loginWindow = loginWindow;
        this.libraryManager = libraryManager;
        this.libraryClient = libraryClient;
        setLoginListeners();

    }

    public void setLoginListeners()
    {
        libraryClient.getQuitterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("----- quit client ------");
                System.exit(0);
            }
        });

        libraryManager.getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("----- quit manager------");
                System.exit(0);
            }
        });

        loginWindow.getLoginUserButton().addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                System.out.println("---- login ----");
                loginUser();
            }
        });

        loginWindow.getLoginAdminButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                loginAdmin();

            }
        });
    }

    public void loginUser()
    {
        String username = loginWindow.getUsernameField().getText();
        String password = loginWindow.getPasswordField().getText();

        // Appeler la fonction seConnecter avec les données du champ de saisie
        Client client = Client.seConnecter(username, password);

        if (client != null)
        {
            // Si la connexion est réussie, afficher le message de bienvenue et ouvrir la fenêtre de la bibliothèque
            loginWindow.getLoginUserButton().addActionListener(e -> showDialog("Bienvenue cher client"));
            //LibraryClient libraryClient = new LibraryClient();
            libraryClient.setUsername(username);
            libraryClient.setVisible(true);
        }
        else
        {
            // Sinon, afficher un message d'erreur
            loginWindow.getLoginUserButton().addActionListener(e -> showDialog("Le nom d'utilisateur et/ou le mot de passe ne sont pas valides"));
        }

    }

    public void loginAdmin()
    {
        String username = loginWindow.getUsernameField().getText();
        String password = loginWindow.getPasswordField().getText();

        Bibliothecaire bibliothecaire = Bibliothecaire.seConnecter(username, password);

        if (bibliothecaire != null)
        {
            loginWindow.getLoginAdminButton().addActionListener(e -> showDialog("Bienvenue cher administrateur"));
            LibraryManager libraryManager = new LibraryManager();
            libraryManager.setAdminName(username);
            libraryManager.setVisible(true);
        }

        else
        {
            loginWindow.getLoginAdminButton().addActionListener(e -> showDialog("Le nom d'utilisateur et/ou le mot de passe ne sont pas valides"));
        }
    }

    private void showDialog(String message)
    {

        JOptionPane.showMessageDialog(null, message);
    }







}