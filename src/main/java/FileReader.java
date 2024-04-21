import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileReader {
    private String fileUri;
    @Getter
    private ArrayList <String> fileData;

    public FileReader(String fileUri){
        this.fileUri = fileUri;
        fileData = new ArrayList<>();
    }

    public void addFileData(){
        Path filePath = Paths.get(fileUri);
        try {
            Files.lines(filePath).forEach(string -> {
                fileData.add(string);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
