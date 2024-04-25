import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FileProcessor {
    private final long FILE_SIZE_LIMIT =  524_288_000;
    private String inputFileUri;
    private String outputPath;
    private String ordering;
    private String suffix;
    File inputFile;

    public FileProcessor(String inputFileUri, String outputPath, String ordering, String suffix) {
        this.inputFileUri = inputFileUri;
        this.outputPath = outputPath;
        this.ordering = ordering;
        this.suffix = suffix;
        inputFile = new File(inputFileUri);
    }

    public void processFile() {
        if(checkInputFile()){
            FileReader fileReader = new FileReader(inputFileUri);
            try {
                fileReader.addFileData();
                ArrayList<String> sortedFileData = sort(fileReader.getFileData(), ordering);
                FileWriter fileWriter = new FileWriter(outputPath);
                fileWriter.createFile(sortedFileData, inputFile.getName(), suffix);
            } catch (Exception e) {
                System.out.println("Something went wrong during file processing "
                        + inputFile.getPath()
                        + "It can not be sorted");
                e.printStackTrace();
            }
        }

    }

    private boolean checkInputFile(){
        if(inputFile.length() > FILE_SIZE_LIMIT){
            System.out.println("File " + inputFile.getPath() + " is is too large. MAX file size to process = " + FILE_SIZE_LIMIT / (1024*1024) + " MB");
            return false;
        }
        return true;
    }

    private ArrayList<String> sort(ArrayList<String> inputFileData, String ordering){
        Collections.sort(inputFileData);
        if (ordering.equalsIgnoreCase("desc")){
            Collections.reverse(inputFileData);
        }
         return inputFileData;
    }
}
