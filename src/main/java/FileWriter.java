import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileWriter {
    private String targetOutputPath;

    public FileWriter(String targetOutputPath){
        this.targetOutputPath = targetOutputPath;
    }

    public void createFile(ArrayList<String> data, String fileName, String suffix) throws IOException{
        Path outputPath = Paths.get(this.targetOutputPath);
        if (!Files.exists(outputPath)) {
            try {
                Files.createDirectories(outputPath);
            } catch (IOException e) {
                System.out.println("It looks like outputPath " + outputPath + " can not be created");
                e.printStackTrace();
            }
        }
        String outputFileUri = targetOutputPath
                + fileName.substring(0, fileName.lastIndexOf("."))
                + suffix
                + fileName.substring(fileName.lastIndexOf("."));
        Path outputFilePath = Paths.get(outputFileUri);
        Files.write(outputFilePath, data);
    }

}
