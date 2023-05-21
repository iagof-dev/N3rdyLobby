package software.n3rdylobby;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import software.n3rdylobby.events.LobbyEvents;

public final class N3rdyLobby extends JavaPlugin implements Listener {


    public static FileConfiguration config;

    @Override
    public void onEnable() {
        config = this.getConfig();
        config.options().copyHeader(true);
        config.options().copyDefaults(true);
        saveConfig();
        spawn.LoadSpawn(config);

        LobbyEvents lb_events = new LobbyEvents();
        getCommand("build").setExecutor(new software.n3rdylobby.commands.build());

        this.getServer().getPluginManager().registerEvents(lb_events, this);
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("definirspawn").setExecutor(new software.n3rdylobby.commands.setspawn());
        this.getCommand("definirspawn").setExecutor(new software.n3rdylobby.commands.setspawn());




        Bukkit.getConsoleSender().sendMessage("§6[§bN3rdyLobby§g] §f| §6Iniciado!");
    }
    @Override
    public void onDisable() {
        saveConfig();
        Bukkit.getConsoleSender().sendMessage("§6[§bN3rdyLobby§g] §f| §cDesligado!");
    }

}
