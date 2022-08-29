package generalrs.Gigaconomy.Economy.Data;

import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class Languages {
    private static FileConfiguration customConfig;

    public Languages() {

        File customConfigFile = new File(Gigaconomy.instance.getDataFolder(), "en_GB.yml");
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


    public static String getString(String id){
        if (customConfig.getString(id)==null){
            return "ALlAH HUMDUBALEH "+id+" is not set";
        }
        return ChatColor.translateAlternateColorCodes('&',customConfig.getString(id));

    }
    public static String parsePlaceHolder(String str,String placeholder,String value){
        String ph = "%"+placeholder+"%";
        return str.replace(ph,value);
    }
}
