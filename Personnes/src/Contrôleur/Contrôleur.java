package Contrôleur;
import ClassesMétier.*;
import InterfacesGraphiques.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public final class Contrôleur implements ActionListener
{
    private CoucheAccèsDonnées model;
    private VueGénérale vue;

    public Contrôleur(CoucheAccèsDonnées model, VueGénérale vue)
    {
        this.model = model;
        this.vue = vue;
        //setContrôleur(this);
    }

    public void run()
    {
        //vue.run();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == ActionsContrôleur.LOGINADMIN)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.LOGINUSER)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.EMPRUNTER)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.RENDRE)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.RECHERCHER)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.EXIT)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.AJOUT)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.SUPPRIMER)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.MODIFIER)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.AFFICHER)
        {

        }

        if(e.getActionCommand() == ActionsContrôleur.NETTOYER)
        {

        }
    }
}
