package software.n3rdylobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import software.n3rdylobby.N3rdyLobby;
import software.n3rdylobby.entity.player;

import java.util.UUID;

public class build implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        UUID puid = p.getUniqueId();
        if(!(player.can_build.get(puid) != null || player.can_build.get(p.getUniqueId())))
        {
            if (player.can_build.get(puid) != null || player.can_build.get(p.getUniqueId()) != false) {
                //remove
                player.can_build.put(puid, false);
                p.sendMessage("§cModo Construir DESABILITADO!");
            }
            else{
                //adiciona
                player.can_build.put(puid, true);
                p.sendMessage("§aModo Construir HABILITADO!");
            }
        }
        return true;


    }
}
