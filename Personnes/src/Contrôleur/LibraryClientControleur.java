package Contrôleur;

import Modèle.ClassesMétier.*;
import Modèle.CoucheAccèsDonnées.*;
import Vue.InterfacesGraphiques.LibraryClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryClientControleur
{
    private LibraryClient libraryClient;

    private Bibliothecaire bibliothecaire;

    private Livre livre;
    private Editeur editeur;
    private Auteur auteur;
    private Client client;

    public LibraryClientControleur(LibraryClient libraryClient)
    {
        this.libraryClient = libraryClient;
        setClientListeners();
    }

    public void setClientListeners()
    {
        libraryClient.getQuitterButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                exitApplication();
            }
        });

        libraryClient.getEmprunterButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                emprunter();
            }
        });

        libraryClient.getRendreButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                rendre();
            }
        });

        libraryClient.getRechercherButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                rechercher();
            }
        });
    }



    public void exitApplication()
    {
        System.out.println("Test exit");
        int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)
        {
            System.exit(0); // Ferme la fenêtre
        }
    }

    public void emprunter()
    {
        libraryClient.getEmprunterButton().addActionListener(e -> showDialog("Emprunt effectue"));
    }

    public void rendre()
    {
        libraryClient.getRendreButton().addActionListener(e -> showDialog("Rendu effectue"));
    }

    public void rechercher()
    {
        libraryClient.getRechercherButton().addActionListener(e -> showDialog("Recherche effectue"));
    }

    private void showDialog(String message)
    {

        JOptionPane.showMessageDialog(null, message);
    }
}
