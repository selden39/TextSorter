import java.io.File;

public class Main {

    public static void main(String[] args) {

        String settingsUri;
        /* block for JAR
        if (args.length == 0) {
            settingsUri = "settings.txt";
            System.out.println("default sett");
        } else {
            settingsUri = args[0];
            System.out.println("manual sett");
        }*/

        settingsUri = "src/main/resources/settings.txt";

        // ------ получаем настройки
        SettingsProcessor settingsProcessor = new SettingsProcessor(settingsUri);
        settingsProcessor.addSettings();

        String inputUri = settingsProcessor.getSettings().get("inputpath");
        String outputUri = settingsProcessor.getSettings().get("outputpath");
        String ordering = settingsProcessor.getSettings().get("orderby");
        String suffix = settingsProcessor.getSettings().get("suffix");

        File file = new File(inputUri);
        if (file.exists()) {
            if (file.isFile()) {
                processFile(inputUri, outputUri, ordering, suffix);
            }
            if (file.isDirectory()) {
                System.out.println("this is directory");
                for (File internalFile : file.listFiles()) {
                    if (internalFile.isDirectory()){
                        continue;
                    }
                    inputUri = internalFile.getPath();
                    processFile(inputUri, outputUri, ordering, suffix);
                }
            }
        } else {
            System.out.println("there is no such file or directory");
        }
    }

    public static void processFile(String inputUri, String outputUri, String ordering, String suffix){
        FileProcessor fileProcessor = new FileProcessor(inputUri, outputUri, ordering, suffix);
        fileProcessor.processFile();
    }

}