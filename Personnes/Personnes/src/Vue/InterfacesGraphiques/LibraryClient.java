package Vue.InterfacesGraphiques;

import Modèle.ClassesMétier.Client;
import Contrôleur.ActionsContrôleur;
import Modèle.ClassesMétier.Livre;
import Modèle.CoucheAccèsDonnées.CoucheAccèsDonnées;
import Modèle.CoucheAccèsDonnées.CoucheAccèsDonnéesDAO;
import Vue.VueLibraryClient;
import Contrôleur.Contrôleur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LibraryClient extends JFrame implements VueLibraryClient
{

    private Client client;

    private CoucheAccèsDonnées coucheAccèsDonnées;

    private LibraryClient libraryClient;

    private JTextField usernameField;
    private JButton emprunterButton;
    private JButton rendreButton;
    private JButton rechercherButton;
    private JButton quitterButton;

    public JTextField getUsernameField()
    {
        return usernameField;
    }
    public void setUsername(String username)
    {
        usernameField.setText(username);
    }

    public JButton getEmprunterButton()
    {
        return emprunterButton;
    }

    public JButton getRendreButton()
    {
        return rendreButton;
    }

    public JButton getRechercherButton()
    {
        return rechercherButton;
    }

    public JButton getQuitterButton()
    {
        return quitterButton;
    }

    private static LibraryClient instance;
    public static LibraryClient getLibraryClient()
    {
        if (instance == null) {
            instance = new LibraryClient();
        }
        return instance;
    }
    public LibraryClient()
    {
        coucheAccèsDonnées = new CoucheAccèsDonnéesDAO();
        initializeUI();
    }

    private void initializeUI()
    {
        setTitle("Gestionnaire de bibliothèque");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panneau principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Première ligne
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        usernameField = new JTextField(15);
        usernameField.setEditable(false);
        topPanel.add(usernameField);

        emprunterButton = new JButton("EMPRUNTER");
        rendreButton = new JButton("RENDRE");
        rechercherButton = new JButton("RECHERCHER");
        quitterButton = new JButton("QUITTER");
        topPanel.add(emprunterButton);
        topPanel.add(rendreButton);
        topPanel.add(rechercherButton);
        topPanel.add(quitterButton);

        emprunterButton.setActionCommand(ActionsContrôleur.EMPRUNTER);
        rendreButton.setActionCommand(ActionsContrôleur.RENDRE);
        rechercherButton.setActionCommand(ActionsContrôleur.RECHERCHER);
        quitterButton.setActionCommand(ActionsContrôleur.EXIT);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Deuxième ligne avec une JTable
        String[] columnNames = {"ID","Auteur", "Livre", "Editeur", "Quantité"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Charger les données depuis le fichier
        loadDataFromFile("LendBooks.txt", tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }

    private void loadDataFromFile(String filePath, DefaultTableModel tableModel)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(", ");
                if (data.length >= 7) // Vérifiez que nous avons au moins 5 colonnes dans chaque ligne
                {
                    // Extraire uniquement les colonnes souhaitées
                    String id = data[0];
                    String auteur = data[2];
                    String titre = data[1];
                    String editeur = data[3];
                    String quantite = data[7];

                    // Ajouter ces données à la table
                    tableModel.addRow(new Object[]{id,auteur, titre, editeur, quantite});
                }
            }
        } catch (IOException e)
        {
            showMessage("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    private void ClearTable(DefaultTableModel tableModel)
    {
        for (int i = 0; i < tableModel.getRowCount(); i++)
        {
            tableModel.setValueAt("", i, 1);
        }
    }

    public void emprunterButtonListener(ActionListener listener)
    {
        emprunterButton.addActionListener(listener);
    }

    public void rendreButtonListener(ActionListener listener)
    {
        rendreButton.addActionListener(listener);
    }

    public void rechercherButtonListener(ActionListener listener)
    {
        rechercherButton.addActionListener(listener);
    }

    public void quitterButtonListener(ActionListener listener)
    {
        quitterButton.addActionListener(listener);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    private void showDialog(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void run()
    {
        this.setVisible(true);
    }

    @Override
    public void setContrôleurClient(Contrôleur Contrôleur)
    {

        //this.libraryClient = this;

        emprunterButton.setActionCommand(ActionsContrôleur.EMPRUNTER);
        rendreButton.setActionCommand(ActionsContrôleur.RENDRE);
        rechercherButton.setActionCommand(ActionsContrôleur.RECHERCHER);
        quitterButton.setActionCommand(ActionsContrôleur.EXIT);

        emprunterButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Emprunter");
        rendreButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Rendre");
        rechercherButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Rechercher");
        quitterButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Quitter");

    }

    public void Emprunter()
    {
        LibraryManager libraryManager = new LibraryManager();
        libraryManager.setAdminName("Emprunt");
        libraryManager.setVisible(true);

        // Masquer les boutons
        libraryManager.getAddButton().setVisible(false);
        libraryManager.getDeleteButton().setVisible(false);
        libraryManager.getEditButton().setVisible(false);
        libraryManager.getViewButton().setVisible(false);
        libraryManager.getExitButton().setVisible(false);

        // Demander l'ID du livre à emprunter
        String input = JOptionPane.showInputDialog(null, "Entrez l'ID du livre à emprunter:", "Emprunter Livre",
                JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.isEmpty())
        {
            showDialog("ID du livre non valide.");
            libraryManager.setVisible(false);
            return;
        }

        try
        {
            int id = Integer.parseInt(input);
            if (coucheAccèsDonnées.emprunterLivre(id))
            {
                showDialog("Vous avez emprunté le livre avec succès.");
                libraryManager.setVisible(false);
            }
            else
            {
                showDialog("Impossible d'emprunter le livre. Vérifiez l'ID et la disponibilité.");
            }
        }
        catch (NumberFormatException e)
        {
            showDialog("ID du livre non valide.");
        }
    }

    public void Rendre()
    {
        // Demander l'ID du livre à rendre
        String input = JOptionPane.showInputDialog(null, "Entrez l'ID du livre à rendre:", "Rendre Livre",
                JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.isEmpty())
        {
            showDialog("ID du livre non valide.");
            return;
        }

        try
        {
            int id = Integer.parseInt(input);
            if (coucheAccèsDonnées.rendreLivre(id))
            {
                showDialog("Vous avez rendu le livre avec succès.");
            }
            else
            {
                showDialog("Impossible de rendre le livre. Vérifiez l'ID.");
            }
        }
        catch (NumberFormatException e)
        {
            showDialog("ID du livre non valide.");
        }
    }

    public void RechercherLivre()
    {
        String motCle = JOptionPane.showInputDialog(null, "Entrez le titre du livre à rechercher:", "Rechercher Livre",
                JOptionPane.QUESTION_MESSAGE);
        if (motCle == null || motCle.isEmpty()) {
            showDialog("Titre du livre non valide.");
            return;
        }

        List<Livre> resultats = coucheAccèsDonnées.rechercherLivre(motCle);

        if (resultats.isEmpty())
        {
            showDialog("Aucun livre trouvé pour le titre: " + motCle);
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            for (Livre livre : resultats)
            {
                sb.append("ID: ").append(livre.getID()).append(", ");
                sb.append("Titre: ").append(livre.getTitre()).append(", ");
                sb.append("Auteur: ").append(livre.getAuteur()).append("\n");
            }
            showDialog(sb.toString());
        }
    }
}
