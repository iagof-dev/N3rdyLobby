package software.n3rdylobby.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import software.n3rdylobby.Handles.HandleGUI;
import software.n3rdylobby.N3rdyLobby;
import software.n3rdylobby.config_spawn;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LobbyEvents implements Listener {

    //Config.yml
    public static boolean tnt_explode = false;
    public static boolean spawn_void = true;
    public static boolean fall_damage = false;
    //Place, Break, Interact with Blocks
    public static boolean PBI_blocks = false;
    public static boolean always_day = true;
    public static boolean block_drop = true;
    public static boolean weather_static = true;
    public static boolean player_vs_player = false;


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {

        boolean membro = true;
        String perm = "n3rdydev.0000.buildon";
        Player p = (Player) e.getPlayer();
        e.setJoinMessage("");
        p.setPlayerListName("§7");

        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());

        if (p.hasPermission("n3rdydev.cargo.vip")) {
            e.setJoinMessage("§d[VIP]§f " + e.getPlayer().getName() + " §6entrou neste lobby!");
            p.setDisplayName("§d[VIP] " + e.getPlayer().getName());
            p.setPlayerListName("§d[VIP] " + p.getName());

            p.setAllowFlight(true);
            membro = false;
        }
        if (p.hasPermission("n3rdydev.cargo.admin")) {
            p.setDisplayName("§c[Admin] " + p.getName());
            p.setPlayerListName("§c[Admin] " + p.getName());
            p.setAllowFlight(true);

        }
        if (p.hasPermission("n3rdydev.cargo.developer")) {
            Player joinedPlayer = e.getPlayer();
            p.setDisplayName("§6[Dev] " + p.getName());
            p.setPlayerListName("§6[Dev] " + p.getName());
            p.setAllowFlight(true);
        }

        if(p.hasPermission(perm)){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset " + perm);
        }

        Location loc = new Location(p.getWorld(), config_spawn.spawn_x, config_spawn.spawn_y, config_spawn.spawn_z);
        p.teleport(loc);
        p.getInventory().clear();


        //ITENS
        ItemStack bussola = new ItemStack(Material.COMPASS, 1);
        ItemMeta bussola_meta = bussola.getItemMeta();
        bussola_meta.setDisplayName("§f§b§eServidores§f §7(Clique para Selecionar)");
        bussola_meta.setLore(Arrays.asList("Lista de Servidores", "Clique em um para entrar"));
        bussola.setItemMeta(bussola_meta);
        p.getInventory().setItem(4, bussola);

        for(Player allp : Bukkit.getOnlinePlayers()){
            allp.setScoreboard(software.n3rdylobby.Handles.HandleScoreboard.sb_default(allp));
        }
        p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (p.hasPermission("n3rdydev.0000.buildon")) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
            if (e.getView().getTitle().equals("Lista de Servidores")) {

                switch (e.getCurrentItem().getType()) {
                    case GRASS:
                        p.closeInventory();
                        sendServer(p, "survival");
                        break;
                }

            }
        }
    }

    private void sendServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);

        player.sendPluginMessage(N3rdyLobby.getPlugin(), "BungeeCord", out.toByteArray());
        player.sendMessage("§aRedirecionando para " + server + ".");
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = (Player) e.getPlayer();
        p.setScoreboard(software.n3rdylobby.Handles.HandleScoreboard.sb_default(p));
        e.setQuitMessage("");
    }

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent e) {
        e.setDeathMessage("");
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {
        if (block_drop != false) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (fall_damage != true) {
            if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL))) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onOperatorSet(PlayerCommandPreprocessEvent event) {
        if (event.getPlayer().hasPermission("n3rdydev.comandos")) {

        } else {
            String cmd = event.getMessage();
            String[] blocked_cmds = {"/plugins", "/plugins ", "/pl", "/pl ", "/about", "/about ", "/ver", "/ver "};
            for (int i = 0; i < blocked_cmds.length; i++) {
                if (cmd.startsWith(blocked_cmds[i])) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("§cComando Inexistente...");
                }
            }
        }

    }

    @EventHandler
    public void PlayerHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void PlayerBreakBlock(BlockBreakEvent e) {
        if (e.getPlayer().hasPermission("n3rdydev.0000.buildon")) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerPlaceBlock(BlockPlaceEvent e) {
        if (e.getPlayer().hasPermission("n3rdydev.0000.buildon")) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (e.getPlayer().hasPermission("n3rdydev.0000.buildon")) {
            e.setCancelled(false);
        } else {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (e.getItem() != null && e.getItem().getType().equals(Material.COMPASS)) {
                    p.openInventory(HandleGUI.Server_GUI(p));
                    e.setCancelled(false);
                }
                e.setCancelled(true);
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void WeatherChange(WeatherChangeEvent e) {
        if (weather_static != false) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (spawn_void != false) {
            if (e.getPlayer().getLocation().getY() < 0) {
                Location loc = new Location(e.getPlayer().getWorld(), config_spawn.spawn_x, config_spawn.spawn_y, config_spawn.spawn_z);
                e.getPlayer().teleport(loc);
            }
        }

    }

    @EventHandler
    public void OnPlayerPvP(EntityDamageEvent e) {
        if (player_vs_player != false) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerChatEvent(PlayerChatEvent e) {
        String cargo = "§7";
        Player p = e.getPlayer();
        if (p.hasPermission("n3rdydev.cargo.vip")) {
            cargo = "§d[VIP]§f ";
        }

        if (p.hasPermission("n3rdydev.cargo.admin")) {
            cargo = "§c[ADMIN]§f ";
        }
        if (p.hasPermission("n3rdydev.cargo.developer")) {
            cargo = "§6[DEV]§f ";
        }
        e.setFormat(cargo + e.getPlayer().getName() + ": " + e.getMessage());
    }

    @EventHandler
    public void OnTntExplode(EntityDamageEvent e) {
        if (tnt_explode != true) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void OnEntityExplode(EntityExplodeEvent e) {
        if (tnt_explode != true) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }





}
