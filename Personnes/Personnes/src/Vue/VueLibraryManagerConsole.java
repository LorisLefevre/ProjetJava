package Vue;

import Contrôleur.Contrôleur;

public class VueLibraryManagerConsole implements VueLibraryManager
{
    public void loadDataFromFile(String filePath)
    {
        System.out.println("Loading data from file: " + filePath);
    }

    @Override
    public void loadDataFromFileStream(String filePath)
    {
        System.out.println("Loading data from file: " + filePath);

    }

    @Override
    public void setContrôleurManager(Contrôleur contrôleur)
    {
        contrôleur = contrôleur;
    }
}
