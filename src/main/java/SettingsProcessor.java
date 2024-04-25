import lombok.Getter;

import java.util.HashMap;

public class SettingsProcessor {
    public static final String DEFAULT_INPUT_PATH = "input";
    public static final String DEFAULT_OUTPUT_PATH = "output";
    public static final String DEFAULT_ORDERING = "asc";
    public static final String DEFAULT_SUFFIX = "sorted";
    String settingsUri;
    @Getter
    HashMap <String, String> settings;

    public SettingsProcessor(String settingsUri){
        this.settingsUri = settingsUri;
        settings = new HashMap<>();
    }

    public void addSettings(){
        FileReader fileReader = new FileReader(settingsUri);
        try {
            fileReader.addFileData();
        } catch (Exception e) {
            System.out.println("Default settings will be applied, because settings.txt not found");
            checkSettings();
        }
        fileReader.getFileData().forEach(settingString -> {
            if (settingString.indexOf("=") > -1) {
                String key = settingString.substring(0, settingString.indexOf("=")).trim();
                String value = settingString.substring(settingString.indexOf("=") + 1).trim();
                settings.put(key.toLowerCase(), value.toLowerCase());
            }
        });
        //settings.forEach((key, value) -> System.out.println(key + " - " + value));
        checkSettings();
    }
    public void checkSettings() {
        if (settings.get("inputpath") == null) {
            settings.put("inputpath", DEFAULT_INPUT_PATH);
        }
        if (settings.get("outputpath") == null) {
            settings.put("outputpath", DEFAULT_OUTPUT_PATH);
        }
        if (settings.get("orderby") == null) {
            settings.put("orderby", DEFAULT_ORDERING);
        }
        if (settings.get("suffix") == null) {
            settings.put("suffix", DEFAULT_SUFFIX);
        }
        //settings.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
