package Modèle.CoucheAccèsDonnées;

import Modèle.ClassesMétier.*;
import Modèle.CoucheAccèsDonnées.*;
import java.util.ArrayList;

public class CoucheAccèsDonnéesDAO implements CoucheAccèsDonnées
{
    private ArrayList<Livre> livres;
    private ArrayList<Editeur> editeurs;
    private ArrayList<Auteur> auteurs;

    private static int IdCourant = 1;

    public CoucheAccèsDonnéesDAO()
    {
        livres = new ArrayList<>();
        editeurs = new ArrayList<>();
        auteurs = new ArrayList<>();
    }

    @Override
    public int AjoutLivre(Livre livre)
    {
        return 0;
    }

    @Override
    public boolean ModifierLivre(Livre livre)
    {
        /*for(int i = 0; i < livres.size(); i++)
        {
            if(livres.get(i).getId() == livre.getId())
            {
                livres.set(i, livre);
                return true;
            }
        }
        return false;*/

        return false;
    }

    @Override
    public boolean SupprimerLivre(int idLivre)
    {
        return false;
    }

    @Override
    public boolean SupprimerLivre(Livre livre)
    {
        return false;
    }

    @Override
    public Livre RechercherLivre(int idLivre)
    {
        return null;
    }

    @Override
    public ArrayList<Livre> ListerLivres()
    {
        return null;
    }

    @Override
    public int AjoutAuteur(Auteur auteur)
    {
        return 0;
    }

    @Override
    public boolean ModifierAuteur(Auteur auteur)
    {
        return false;
    }

    @Override
    public boolean SupprimerAuteur(int idAuteur)
    {
        return false;
    }

    @Override
    public boolean SupprimerAuteur(Auteur auteur)
    {
        return false;
    }

    @Override
    public ArrayList<Auteur> ListerAuteurs()
    {
        return null;
    }

    @Override
    public int AjoutEditeur(Editeur editeur)
    {
        return 0;
    }

    @Override
    public boolean ModifierEditeur(Editeur editeur)
    {
        return false;
    }

    @Override
    public boolean SupprimerEditeur(int idEditeur)
    {
        return false;
    }

    @Override
    public boolean SupprimerEditeur(Editeur editeur)
    {
        return false;
    }

    @Override
    public ArrayList<Editeur> ListerEditeurs()
    {
        return null;
    }


}
