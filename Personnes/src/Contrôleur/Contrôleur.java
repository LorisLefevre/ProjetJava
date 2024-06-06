package Contrôleur;
import Modèle.ClassesMétier.Bibliothecaire;
import Modèle.ClassesMétier.Client;
import Modèle.CoucheAccèsDonnées.*;
import Vue.*;
import Vue.InterfacesGraphiques.LibraryClient;
import Vue.InterfacesGraphiques.LibraryManager;
import Vue.InterfacesGraphiques.LoginWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JColorChooser.showDialog;


public final class Contrôleur implements ActionListener
{
    private CoucheAccèsDonnées model;
    private VueLogin vue;

    private VueLibraryClient vueClient;

    private VueLibraryManager vueManager;
    private LoginWindow loginWindow;

    private LibraryManager libraryManager;

    private LibraryClient libraryClient;

    public Contrôleur(CoucheAccèsDonnées model, VueLogin vue)
    {
        this.model = model;
        this.vue = vue;
        this.vue.setContrôleur(this);
    }

    public void ContrôleurClient(VueLibraryClient vueClient)
    {
        this.libraryClient = (LibraryClient) vueClient;
        vueClient.setContrôleurClient(this);
    }

    public void ContrôleurManager(VueLibraryManager vueManager)
    {
        this.libraryManager = (LibraryManager) vueManager;
        vueManager.setContrôleurManager(this);
    }


    public void run()
    {
        System.out.println("Ceci est le Contrôleur");
        this.vue.run();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == ActionsContrôleur.LOGINADMIN)
        {
            vue.LoginAdmin();
            vueManager = LibraryManager.getLibraryManager();
            ContrôleurManager(vueManager);

        }

        if(e.getActionCommand() == ActionsContrôleur.LOGINUSER)
        {
            vue.LoginClient();
            vueClient = LibraryClient.getLibraryClient(); // Récupérez l'instance appropriée
            ContrôleurClient(vueClient);
        }

        if(e.getActionCommand() == ActionsContrôleur.EMPRUNTER)
        {
            System.out.println("Bouton Emprunter pressé");
            libraryClient.Emprunter();
        }

        if(e.getActionCommand() == ActionsContrôleur.RENDRE)
        {
            System.out.println("Bouton Rendre pressé");
            libraryClient.Rendre();
        }

        if(e.getActionCommand() == ActionsContrôleur.RECHERCHER)
        {
            System.out.println("Bouton Rechercher pressé");
            libraryClient.RechercherLivre();
        }

        if(e.getActionCommand() == ActionsContrôleur.EXIT)
        {
            System.out.println("Bouton Exit pressé");
            System.exit(0);
        }

        if(e.getActionCommand() == ActionsContrôleur.AJOUT)
        {
            System.out.println("Bouton Ajouter pressé");
            libraryManager.Ajoute();
        }

        if(e.getActionCommand() == ActionsContrôleur.SUPPRIMER)
        {
            System.out.println("Bouton Supprimer pressé");
            libraryManager.Supprime();
        }

        if(e.getActionCommand() == ActionsContrôleur.MODIFIER)
        {
            System.out.println("Bouton Modifier pressé");
            libraryManager.Modifier();
        }

        if(e.getActionCommand() == ActionsContrôleur.AFFICHER)
        {
            System.out.println("Bouton Afficher pressé");
            libraryManager.Afficher();
        }
    }
}
