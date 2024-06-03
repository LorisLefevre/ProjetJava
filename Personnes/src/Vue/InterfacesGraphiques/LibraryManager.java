package Vue.InterfacesGraphiques;

import Contrôleur.Contrôleur;
import Contrôleur.ActionsContrôleur;
import Vue.VueLibraryManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class LibraryManager extends JFrame implements VueLibraryManager
{
    private JTable table;
    private DefaultTableModel model;


    private JTextField adminNameField;

    public JTextField getAdminNameField()
    {
        return adminNameField;
    }

    public void setAdminName(String adminName)
    {
        adminNameField.setText(adminName);
    }

    private JButton addButton, deleteButton, viewButton, editButton, clearButton, exitButton;

    public JButton getAddButton()
    {
        return addButton;
    }

    public JButton getDeleteButton()
    {
        return deleteButton;
    }

    public JButton getViewButton()
    {
        return viewButton;
    }

    public JButton getEditButton()
    {
        return editButton;
    }

    public JButton getClearButton()
    {
        return clearButton;
    }

    public JButton getExitButton()
    {
        return exitButton;
    }


    public LibraryManager()
    {
        setTitle("Gestion de la bibliothèque de Sclessin");
        setSize(700, 270);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Création du modèle de la table avec les colonnes
        model = new DefaultTableModel();
        model.addColumn("Labels");
        model.addColumn("TextFields");

        // Ajout des données pour les labels et les champs de texte
        String[] labels = {"ID du livre", "Titre du livre", "Modèle.CoucheAccèsDonnées.ClassesMétier.Auteur", "Modèle.CoucheAccèsDonnées.ClassesMétier.Editeur", "Année de publication", "Numéro ISBN", "Nombre de copies"};
        String[] textFields = new String[labels.length];

        for (int i = 0; i < labels.length; i++) {
            model.addRow(new String[]{labels[i], textFields[i]});
        }

        // Création de la table avec le modèle
        table = new JTable(model);
        table.setRowHeight(25); // Hauteur des lignes

        // Ajout de la table à un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 200)); // Ajustement de la taille du JScrollPane

        // Ajout des boutons
        adminNameField = new JTextField(20);
        adminNameField.setEditable(false);
        addButton = new JButton("Ajouter");
        deleteButton = new JButton("Supprimer");
        viewButton = new JButton("Regarder");
        editButton = new JButton("Modifier"); //
        clearButton = new JButton("Effacer");
        exitButton = new JButton("Quitter");

        // Création d'un panneau pour la table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Création d'un panneau pour les boutons avec un espace vertical en haut
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7, 10, 10)); // 6 boutons avec un espacement de 10 pixels
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Ajout d'un espace vertical en bas
        buttonPanel.add(adminNameField);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        addButton.setActionCommand(ActionsContrôleur.AJOUT);
        deleteButton.setActionCommand(ActionsContrôleur.SUPPRIMER);
        viewButton.setActionCommand(ActionsContrôleur.MODIFIER);
        editButton.setActionCommand(ActionsContrôleur.AFFICHER);
        clearButton.setActionCommand(ActionsContrôleur.NETTOYER);
        exitButton.setActionCommand(ActionsContrôleur.EXIT);

        // Création d'un panneau principal avec une disposition BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH); // Boutons au-dessus
        mainPanel.add(tablePanel, BorderLayout.CENTER); // Table en dessous

        // Ajout du panneau principal à la JFrame
        getContentPane().add(mainPanel);

        //setVisible(true);
    }

    public void addButtonListener(ActionListener listener)
    {
        addButton.addActionListener(listener);
    }

    public void deleteButtonListener(ActionListener listener)
    {
        deleteButton.addActionListener(listener);
    }

    public void editButtonListener(ActionListener listener)
    {
        editButton.addActionListener(listener);
    }

    public void viewButtonListener(ActionListener listener)
    {
        viewButton.addActionListener(listener);
    }

    public void clearButtonListener(ActionListener listener)
    {
        clearButton.addActionListener(listener);
    }

    public void exitButtonListener(ActionListener listener)
    {
        exitButton.addActionListener(listener);
    }

    public void adminNameFieldListener(ActionListener listener)
    {
        adminNameField.addActionListener(listener);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    private void add()
    {
        JFrame tableFrame = new JFrame("Contenu de la JTable");
        tableFrame.setSize(600, 240);

        // Création d'un JScrollPane pour la JTable
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Ajout du JScrollPane à la JFrame
        tableFrame.getContentPane().add(tableScrollPane);

        // Rendre la JFrame visible
        tableFrame.setVisible(true);
    }

    /*private void exit()
    {
        dispose();
        //Que fait dispose ?
        // dispose() permet de libérer les ressources utilisées par la JFrame
        System.exit(0);
    }*/

    private void clear()
    {
        for (int i = 0; i < model.getRowCount(); i++)
        {
            model.setValueAt("", i, 1);
        }
    }
}
