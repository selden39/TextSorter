import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class SettingsProcessor {
    String settingsUri;
    @Getter
    HashMap <String, String> settings;

    public SettingsProcessor(String settingsUri){
        this.settingsUri = settingsUri;
        settings = new HashMap<>();
    }

    public void addSettings(){
        FileReader fileReader = new FileReader(settingsUri);
        fileReader.addFileData();
        fileReader.getFileData().forEach(settingString -> {
            if (settingString.indexOf("=") > -1) {
                String key = settingString.substring(0, settingString.indexOf("=")).trim();
                String value = settingString.substring(settingString.indexOf("=") + 1).trim();
                settings.put(key, value);
            }
        });
        /*settings.forEach((key, value) -> {
            System.out.println(key + " - " + value);
        });*/
    }
}
