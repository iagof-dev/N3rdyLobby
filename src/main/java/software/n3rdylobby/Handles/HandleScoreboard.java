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
