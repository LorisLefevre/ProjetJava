package Contrôleur;

import ClassesMétier.Auteur;
import ClassesMétier.Bibliothecaire;
import ClassesMétier.Editeur;
import ClassesMétier.Livre;
import InterfacesGraphiques.LibraryManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagerContrôleur
{
    private LibraryManager libraryManager;

    private Bibliothecaire bibliothecaire;

    private Livre livre;
    private Editeur editeur;
    private Auteur auteur;

    public LibraryManagerContrôleur(LibraryManager libraryManager)
    {
        this.libraryManager = libraryManager;
        setManagerListeners();
    }

    public void setManagerListeners()
    {
        libraryManager.getAddButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                AddBook();
            }
        });

        libraryManager.getDeleteButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                DeleteBook();
            }
        });

        libraryManager.getViewButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ViewBook();
            }
        });

        libraryManager.getEditButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                EditBook();
            }
        });

        libraryManager.getClearButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ClearFields();
            }
        });

        libraryManager.getExitButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                exitApplication();
            }
        });
    }

    public void AddBook()
    {
        libraryManager.getAddButton().addActionListener(e -> showDialog("Ajout effectue"));
    }

    public void DeleteBook()
    {
        libraryManager.getDeleteButton().addActionListener(e -> showDialog("Suppression effectue"));
    }

    public void ViewBook()
    {
        libraryManager.getViewButton().addActionListener(e -> showDialog("Affichage effectue"));
    }

    public void EditBook()
    {
        libraryManager.getEditButton().addActionListener(e -> showDialog("Modification effectue"));
    }

    public void ClearFields()
    {
        libraryManager.getClearButton().addActionListener(e -> showDialog("Champs vide"));
    }
    public static void exitApplication()
    {
        System.exit(0);
    }

    private void showDialog(String message)
    {

        JOptionPane.showMessageDialog(null, message);
    }
}
