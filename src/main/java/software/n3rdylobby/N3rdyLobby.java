package software.n3rdylobby;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import software.n3rdylobby.events.LobbyEvents;

public final class N3rdyLobby extends JavaPlugin implements Listener, PluginMessageListener {

    public static FileConfiguration config;
    private static N3rdyLobby plugin;

    public static N3rdyLobby getPlugin() {
        return plugin;
    }


    @Override
    public void onEnable() {

        plugin = this;


        //Load Config
        config = this.getConfig();
        config.options().copyHeader(true);
        config.options().copyDefaults(true);
        saveConfig();
        config_spawn.LoadSpawn(config);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

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
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);


        Bukkit.getConsoleSender().sendMessage("§c=====================================");
        Bukkit.getConsoleSender().sendMessage("§6[§bN3rdyLobby§6] §f| §cDesligado!");
        Bukkit.getConsoleSender().sendMessage("§c=====================================");

    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }
}
