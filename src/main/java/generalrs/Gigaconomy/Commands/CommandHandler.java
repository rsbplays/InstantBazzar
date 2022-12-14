package generalrs.Gigaconomy.Commands;

import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.lang.reflect.Field;
import java.util.*;

public class CommandHandler implements CmdExecutor{

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
    public void register(){
        registerCommand("eco","The main eco","",this);

       // commandMap.get("eco").setTabCompleter(this);
    }

    public Command registerCommand(String name,String usageMessage,String description, CmdExecutor executor){
        GigaCommand command = new GigaCommand(name,description,usageMessage,new ArrayList<>());
        bukkitCommandMap.register(name,"Gigaconomy",command);
        commandMap.put(name,command);
        command.setCommandExecutor(executor);

        return command;
    }

    public void addAliases(Command command,String... alias){
        ArrayList<String> list = (ArrayList<String>) command.getAliases();
        for (String ialias:alias){
            list.add(ialias);
        }
        command.setAliases(list);
    }

    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    @Override
    public boolean onCommand(String label, CommandSender sender, String[] args) {
        if (args.length<=0) return false;
        String[] trueargs= new String[args.length-1];
        StringBuilder cmd = new StringBuilder(args[0]);
        int i = 0;
        for (String arg:args){
            if (i!=0){
                trueargs[i-1]=args[i];
            }
            i++;
        }

        for (int b = 0;(trueargs.length)>b;b++){
            cmd.append(" ").append(trueargs[b]);
        }

        if (getCommandMap().containsKey(args[0])){
            bukkitCommandMap.dispatch(sender, cmd.toString());
        }
        return false;

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (args.length==1){

            ArrayList<String> list = new ArrayList<>();
            for (String key:commandMap.keySet()){
                if (key.startsWith(args[0])){
                    list.add(key);
                }
            }
            Collections.sort(list);
            return list;
        }else {
            if (commandMap.containsKey(args[0])){
                GigaCommand gigaCommand = (GigaCommand) commandMap.get(args[0]);
                String[] SL = new String[args.length-1];
                int i = 0;
                for (String arg:args){
                    if (i!=0){
                        SL[i-1]=arg;
                    }
                    i++;
                }
                return gigaCommand.tabComplete(sender,args[0],SL);
            }
            return new ArrayList<>();

        }
    }


}
