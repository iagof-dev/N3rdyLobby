package software.n3rdylobby.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import software.n3rdylobby.N3rdyLobby;

public class setspawn implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp() || sender.hasPermission("n3rdylobby.setspawn") || sender.hasPermission("n3rdylobby.*"))
        {
            String nick = sender.getName();
            Player p =sender.getServer().getPlayer(nick);
            World w = p.getWorld();
            Location loc = p.getLocation();

            int spawn_x = loc.getBlockX();
            int spawn_y = loc.getBlockY();
            int spawn_z = loc.getBlockZ();
            sender.sendMessage("X: " + spawn_x + " | Y: " + spawn_y + " | Z: " + spawn_z);
            software.n3rdylobby.N3rdyLobby.config.set("spawn_x", spawn_x);
            software.n3rdylobby.N3rdyLobby.config.set("spawn_y", spawn_y);
            software.n3rdylobby.N3rdyLobby.config.set("spawn_z", spawn_z);


            sender.sendMessage("§aSpawn definido com sucesso!");

            return true;
        }

        return false;

    }


}
