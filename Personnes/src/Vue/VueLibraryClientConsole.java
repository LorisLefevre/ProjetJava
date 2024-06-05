package Vue;
import Contrôleur.*;
import Vue.InterfacesGraphiques.LibraryClient;



public class VueLibraryClientConsole implements VueLibraryClient
{
    private LibraryClient libraryClient;
    public VueLibraryClientConsole(LibraryClient libraryClient)
    {
        this.libraryClient = libraryClient;
    }
    @Override
    public void run()
    {
        System.out.println("Ouverture Vue Library Client");
    }

    public void setContrôleurClient(Contrôleur contrôleur)
    {
        System.out.println("RRR");
        contrôleur = contrôleur;
    }
}
