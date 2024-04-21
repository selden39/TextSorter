import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String settingsUri = "src/main/resources/settings.txt";

        // ------ получаем настройки
        SettingsProcessor settingsProcessor = new SettingsProcessor(settingsUri);
        settingsProcessor.addSettings();

        String inputFileUri = settingsProcessor.getSettings().get("inputPath");
        String ordering = settingsProcessor.getSettings().get("orderBy") == null ?
                "asc" : settingsProcessor.getSettings().get("orderBy");
        String outputFileUri = settingsProcessor.getSettings().get("outputPath");

        //System.out.println(inputFileUri + " - " + outputFileUri + " - " + ordering);

        // ------ читаем входящий файл
        FileReader fileReader = new FileReader(inputFileUri);
        fileReader.addFileData();
        ArrayList<String> inputFileData = fileReader.getFileData();

        // ------- сортируем
        Collections.sort(inputFileData);
        if(ordering.equalsIgnoreCase("DESC")){
            Collections.reverse(inputFileData);
        }

        // ------ непосредственно запись в файл
        FileWriter fileWriter = new FileWriter(outputFileUri);
        fileWriter.createFile(inputFileData);
    }
}