package software.n3rdylobby;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import software.n3rdylobby.events.LobbyEvents;

public final class N3rdyLobby extends JavaPlugin implements Listener {


    public static FileConfiguration config;

    @Override
    public void onEnable() {



        //Load Config
        config = this.getConfig();
        config.options().copyHeader(true);
        config.options().copyDefaults(true);
        saveConfig();
        config_spawn.LoadSpawn(config);


        //Event Listeners
        LobbyEvents lb_events = new LobbyEvents();
        this.getServer().getPluginManager().registerEvents(lb_events, this);
        this.getServer().getPluginManager().registerEvents(this, this);

        //Registro comandos
        this.getCommand("definirspawn").setExecutor(new software.n3rdylobby.commands.setspawn());
        this.getCommand("build").setExecutor(new software.n3rdylobby.commands.build());
        this.getCommand("spawn").setExecutor(new software.n3rdylobby.commands.spawn());
        this.getCommand("voar").setExecutor(new software.n3rdylobby.commands.voar());
        this.getCommand("servers").setExecutor(new software.n3rdylobby.commands.servers());


        //Inicializado!
        Bukkit.getConsoleSender().sendMessage("§a=====================================");
        Bukkit.getConsoleSender().sendMessage("§6[§bN3rdyLobby§6] §f| §6Iniciado!");
        Bukkit.getConsoleSender().sendMessage("§a=====================================");
    }
    @Override
    public void onDisable() {
        saveConfig();
        Bukkit.getConsoleSender().sendMessage("§c=====================================");
        Bukkit.getConsoleSender().sendMessage("§6[§bN3rdyLobby§6] §f| §cDesligado!");
        Bukkit.getConsoleSender().sendMessage("§c=====================================");
    }

}
