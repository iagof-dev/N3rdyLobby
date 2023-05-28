package software.n3rdylobby.Handles;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;
import me.clip.*;

public class HandleScoreboard extends JavaPlugin {



    public static Scoreboard sb_default(Player p){
        ScoreboardManager sb_manager = Bukkit.getScoreboardManager();
        Scoreboard sbdefault = sb_manager.getNewScoreboard();
        Objective obj = sbdefault.registerNewObjective("title",  "dummy");

        String cargo = "§8[MEMBRO]";

        if (p.hasPermission("n3rdydev.cargo.vip")) {
            cargo = "§d[VIP]";
        }
        if (p.hasPermission("n3rdydev.cargo.admin")) {
            cargo = "§c[ADMIN]";

        }
        if (p.hasPermission("n3rdydev.cargo.developer")) {
            cargo = "§6[DEV]";
        }
        obj.setDisplayName("§l§eN3rdyNetwork");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        String user_role = ("§l§7Grupo:§f " + cargo);
        String separador = "§7--- ---";
        String server_1 = "§l§aLobby: §f%bungee_lobby%";
        String server_ip = ("§7n3rdynetwork.teste");
        server_1 = PlaceholderAPI.setPlaceholders(p, server_1);
        Score role = obj.getScore(user_role);
        Score separator = obj.getScore(separador);
        Score sb_server1 = obj.getScore(server_1);
        Score serverip = obj.getScore(server_ip);
        role.setScore(6);
        separator.setScore(5);
        sb_server1.setScore(4);
        serverip.setScore(0);
        return sbdefault;
    }



    public static Scoreboard sb_staff(Player p){

        ScoreboardManager sb_manager = Bukkit.getScoreboardManager();
        Scoreboard sbdefault = sb_manager.getNewScoreboard();

        Objective obj = sbdefault.registerNewObjective("title",  "dummy");
        obj.setDisplayName("§l§e[N3rdyNetwork]");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        String user_name = ("§l§7Usuario:§f %RainbowColor_" + p.getName() + "%");
        String user_role = ("§l§7Cargo:§f %luckperms_groups%");

        user_name = PlaceholderAPI.setPlaceholders(p, user_name);
        user_role = PlaceholderAPI.setPlaceholders(p, user_role);


        Score name = obj.getScore(user_name);
        Score role = obj.getScore(user_role);

        name.setScore(1);
        role.setScore(0);
        return sbdefault;

    }




}
