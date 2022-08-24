package generalrs.Gigaconomy.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class CommandHandler {

    CommandMap bukkitCommandMap;
    HashMap<String, Command> commandMap = new HashMap<>();

    public CommandHandler() {
        //Gets the bukkit Command Map feild
        final Field bukkitCommandMapF;
        try {
            bukkitCommandMapF = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        bukkitCommandMapF.setAccessible(true);
        //Exrancts the command map
        try {
            bukkitCommandMap = (CommandMap) bukkitCommandMapF.get(Bukkit.getServer());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public Command registerCommand(String name,String usageMessage, CmdExecutor executor){
        Command command = new Command(name) {
            @Override
            public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                return executor.onCommand(commandLabel,sender,args);
            }
        };
        command.setUsage(usageMessage);
        bukkitCommandMap.register(name,"Gigaconomy",command);
        commandMap.put(name,command);
        return command;
    }

    public void addAliases(Command command,String... alias){
        ArrayList<String> list = (ArrayList<String>) command.getAliases();
        for (String ialias:alias){
            list.add(ialias);
        }
        command.setAliases(list);
    }

}
