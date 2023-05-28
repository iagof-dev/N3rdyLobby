package software.n3rdylobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class BlockedCMDS implements CommandExecutor, Listener {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(sender.hasPermission("n3rdydev.comandos.staff")){

        }
        if(sender.hasPermission("n3rdydev.comandos.basicos")){

            return true;
        }


        return true;
    }


}
