package software.n3rdylobby.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import software.n3rdylobby.config_spawn;

public class voar implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player p = (Player)sender;
        if(p.hasPermission("n3rdydev.cargo.vip") || p.hasPermission("n3rdydev.comando.voar")){
            p.setAllowFlight(true);
            p.sendMessage("§fMODO VOAR §aAtivado!");
        }
        return true;
    }
}
