package Vue;

import Modèle.ClassesMétier.Bibliothecaire;
import Modèle.ClassesMétier.Client;
import  Contrôleur.*;
import Vue.InterfacesGraphiques.LoginWindow;
import Vue.VueLogin;

public class VueLoginConsole implements VueLogin
{
    private VueLogin vue;

    private LoginWindow loginWindow;

    public VueLoginConsole(LoginWindow loginWindow)
    {
        System.out.println("T");
        this.loginWindow = loginWindow;
    }

    @Override
    public void run()
    {
        System.out.println("Ouverture MVC Login");
        loginWindow.setVisible(true);
    }

    @Override
    public void setContrôleur(Contrôleur contrôleur)
    {
        loginWindow.addLoginAdminListener(contrôleur);
        loginWindow.addLoginUserListener(contrôleur);
        contrôleur = contrôleur;

    }

    @Override
    public Client LoginClient()
    {
        // TODO Auto-generated method stub
        System.out.println("Tentative de connexion à la fenêtre client");
        String username = loginWindow.getUsername();
        String password = loginWindow.getPassword();
        Client client = Client.seConnecter(username, password);

        if (client != null)
        {
            loginWindow.showMessage("Connexion utilisateur réussie!");
            System.out.println("TRUE");
            return client;
            // Rediriger vers la fenêtre principale ou des fonctions utilisateur
        }
        else
        {
            loginWindow.showMessage("Nom d'utilisateur ou mot de passe utilisateur incorrect.");
            System.out.println("FALSE");
            return null;
        }

    }

    @Override
    public Bibliothecaire LoginAdmin()
    {
        // TODO Auto-generated method stub
        System.out.println("Tentative de connexion à la fenêtre admin");
        return null;
    }
}
