package Vue;

import Contrôleur.*;
import Modèle.ClassesMétier.Bibliothecaire;
import Modèle.ClassesMétier.Client;
import Modèle.ClassesMétier.Livre;
import Modèle.CoucheAccèsDonnées.*;

import java.util.ArrayList;
import java.util.Scanner;

public class VueGénéraleConsole implements VueGénérale
{
    private Contrôleur contrôleur;
    private Scanner scanner;

    public VueGénéraleConsole()
    {
        scanner = new Scanner(System.in);
    }

    @Override
    public void setContrôleur(Contrôleur Contrôleur)
    {
        contrôleur = Contrôleur;
    }

    @Override
    public void run()
    {
        while(true)
        {
            //promptforAction();
        }
    }

    @Override
    public Bibliothecaire LoginAdmin()
    {
        return null;
    }

    @Override
    public Client LoginClient()
    {
        return null;
    }

    @Override
    public void AfficheMessage(String message)
    {
        System.out.println(message);
    }

    @Override
    public void AfficheMessageErreur(String messageErreur)
    {
        System.out.println("Une erreur est survenue : " + messageErreur);
    }

    @Override
    public void MontrerLivres(ArrayList<Livre> livres)
    {
        for(Livre livre : livres)
        {
            System.out.println(livre.toString());
        }
    }

    @Override
    public Livre promptforNewLivre(Livre livre)
    {
        return null;
    }

    @Override
    public Livre promptforUpdateLivre(Livre livre)
    {
        return null;
    }

    @Override
    public Integer promptforLivreID()
    {
        return 0;
    }

    @Override
    public Livre promptforDeleteLivre(Livre livre)
    {
        return null;
    }

    @Override
    public Livre promptforViewLivre(Livre livre)
    {
        return null;
    }
}
