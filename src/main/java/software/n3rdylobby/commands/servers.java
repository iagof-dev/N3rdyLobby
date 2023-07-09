package software.n3rdylobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import software.n3rdylobby.Handles.HandleGUI;

public class servers implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {

        Player p = (Player)sender;
        if(!(sender instanceof Player))
        {
            p.sendMessage("Apenas jogadores podem usar este comando");
            return true;
        }

        p.openInventory(HandleGUI.Server_GUI(p));



        return true;
    }



}
