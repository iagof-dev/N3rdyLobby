package software.n3rdylobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class spawn {
    public static double spawn_x = 0;
    public static double spawn_y = 64;
    public static double spawn_z = 0;
    public static Location spawn_location = new Location(Bukkit.getWorld("world"), spawn_x, spawn_y, spawn_z);

    public static void LoadSpawn(FileConfiguration cfg){

        spawn_x = cfg.getDouble("spawn_x");
        spawn_y = cfg.getDouble("spawn_y");
        spawn_z = cfg.getDouble("spawn_z");



    }


}
