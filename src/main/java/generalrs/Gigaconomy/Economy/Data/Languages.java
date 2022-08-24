package generalrs.Gigaconomy.Economy.Data;

import generalrs.Gigaconomy.Gigaconomy;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class Languages {
    private File customConfigFile;
    private FileConfiguration customConfig;

    public Languages() {
        customConfigFile = new File(Gigaconomy.instance.getDataFolder(),"en_GB.yml");
        if(!customConfigFile.exists()){
            try {
                customConfigFile.createNewFile();
                InputStream inputStream =Gigaconomy.instance.getResource("en_GB.yml");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                String str;
                while((str= bufferedReader.readLine())!=null){
                    stringBuilder.append("\n"+str);
                }

                FileWriter fileWriter = new FileWriter(customConfigFile);
                fileWriter.write(stringBuilder.toString());
                fileWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
    }


    public String getString(String id){
        return customConfig.getString(id);
    }
}
