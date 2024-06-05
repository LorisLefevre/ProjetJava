package Vue;

public class VueLibraryManagerConsole implements VueLibraryManager
{
    public void loadDataFromFile(String filePath)
    {
        System.out.println("Loading data from file: " + filePath);
    }
}
