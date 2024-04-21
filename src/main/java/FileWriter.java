import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileWriter {
    private String fileUri;

    public FileWriter(String fileUri){
        this.fileUri = fileUri;
    }

    public void createFile(ArrayList<String> data){
        Path outputFilePath = Paths.get(fileUri);
        try{
            Files.write(outputFilePath, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
