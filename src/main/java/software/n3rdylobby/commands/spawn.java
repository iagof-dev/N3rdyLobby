package software.n3rdylobby.commands;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import software.n3rdylobby.config_spawn;

public class spawn implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player p = (Player) sender;
        Location spawn_loc = new Location(p.getWorld(), config_spawn.spawn_x, config_spawn.spawn_y, config_spawn.spawn_z);
        p.teleport(spawn_loc);
        return true;
    }

}
