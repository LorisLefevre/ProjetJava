package Contrôleur;
import Modèle.ClassesMétier.Bibliothecaire;
import Modèle.ClassesMétier.Client;
import Modèle.CoucheAccèsDonnées.*;
import Vue.InterfacesGraphiques.LibraryClient;
import Vue.InterfacesGraphiques.LibraryManager;
import Vue.InterfacesGraphiques.LoginWindow;
import Vue.VueGénérale;
import Vue.VueLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public final class Contrôleur implements ActionListener
{
    private CoucheAccèsDonnées model;
    private VueLogin vue;
    private LoginWindow loginWindow;

    private LibraryManager libraryManager;

    private LibraryClient libraryClient;

    public Contrôleur(CoucheAccèsDonnées model, VueLogin vue)
    {
        this.model = model;
        this.vue = vue;
        //this.vue.setContrôleur(this);
        if (vue instanceof LoginWindow)
        {
            this.loginWindow = (LoginWindow) vue;
            this.loginWindow.addLoginAdminListener(this);
            this.loginWindow.addLoginUserListener(this);
        }
    }

    public void run()
    {
        System.out.println("Ceci est le Contrôleur");
        this.vue.run();
        loginWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == ActionsContrôleur.LOGINADMIN)
        {
            System.out.println("Bouton LoginAdmin pressé");
            String username = loginWindow.getUsername();
            String password = loginWindow.getPassword();
            Bibliothecaire admin = Bibliothecaire.seConnecter(username, password);

            if (admin != null)
            {
                loginWindow.showMessage("Connexion admin réussie!");
                LibraryManager libraryManager1 = new LibraryManager();
                libraryManager1.setAdminName(username);
                libraryManager1.setVisible(true);
                // Rediriger vers la fenêtre principale ou des fonctions d'administrateur
            }
            else
            {
                loginWindow.showMessage("Nom d'utilisateur ou mot de passe admin incorrect.");
            }
        }

        if(e.getActionCommand() == ActionsContrôleur.LOGINUSER)
        {
            System.out.println("Bouton LoginUser pressé");
            //vue.LoginClient();
            String username = loginWindow.getUsername();
            String password = loginWindow.getPassword();
            Client client = Client.seConnecter(username, password);

            if (client != null)
            {
                loginWindow.showMessage("Connexion utilisateur réussie!");
                LibraryClient libraryClient1 = new LibraryClient();
                libraryClient1.setUsername(username);
                libraryClient1.setVisible(true);
                // Rediriger vers la fenêtre principale ou des fonctions utilisateur
            }
            else
            {
                loginWindow.showMessage("Nom d'utilisateur ou mot de passe utilisateur incorrect.");
            }
        }

        if(e.getActionCommand() == ActionsContrôleur.EMPRUNTER)
        {
            System.out.println("Bouton Emprunter pressé");
            libraryClient.showMessage("Vous avez emprunté ce livre");
            libraryClient.showMessage("Vous ne pouvez pas emprunter ce livre");
        }

        if(e.getActionCommand() == ActionsContrôleur.RENDRE)
        {
            System.out.println("Bouton Rendre pressé");
            libraryClient.showMessage("Vous avez rendu un livre");
            libraryClient.showMessage("Vous n'avez pas de livre à rendre");
        }

        if(e.getActionCommand() == ActionsContrôleur.RECHERCHER)
        {
            System.out.println("Bouton Rechercher pressé");
            libraryClient.showMessage("Ce livre est disponible");
            libraryClient.showMessage("Ce livre n'est pas disponible");
        }

        if(e.getActionCommand() == ActionsContrôleur.EXIT)
        {
            System.out.println("Bouton Exit pressé");
            System.exit(0);
        }

        if(e.getActionCommand() == ActionsContrôleur.AJOUT)
        {
            System.out.println("Bouton Ajouter pressé");
            libraryManager.showMessage("Un livre a été ajouté");
            libraryManager.showMessage("Une erreur s'est produite lors de l'ajout du livre");
        }

        if(e.getActionCommand() == ActionsContrôleur.SUPPRIMER)
        {
            System.out.println("Bouton Supprimer pressé");
            libraryManager.showMessage("Le livre a été supprimé");
            libraryManager.showMessage("Une erreur s'est produite lors de la suppression du livre");
        }

        if(e.getActionCommand() == ActionsContrôleur.MODIFIER)
        {
            System.out.println("Bouton Modifier pressé");
            libraryManager.showMessage("Le livre a été modifié");
            libraryManager.showMessage("Une erreur s'est produite lors de la modification du livre");
        }

        if(e.getActionCommand() == ActionsContrôleur.AFFICHER)
        {
            System.out.println("Bouton Afficher pressé");
            libraryManager.showMessage("Vous regardez le livre suivant");
            libraryManager.showMessage("Une erreur s'est produite lors de l'affichage du livre");
        }

        if(e.getActionCommand() == ActionsContrôleur.NETTOYER)
        {
            System.out.println("Bouton Nettoyer pressé");
        }
    }
}
