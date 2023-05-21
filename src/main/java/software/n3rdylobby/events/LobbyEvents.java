package software.n3rdylobby.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import software.n3rdylobby.N3rdyLobby;
import software.n3rdylobby.spawn;

import java.util.Arrays;

public class LobbyEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        boolean membro = true;

        Player p = e.getPlayer();

        if(p.hasPermission("n3rdydev.cargo.vip")){
            e.setJoinMessage("§d[VIP]§f " + e.getPlayer().getName() + " §6entrou neste lobby!");
            p.setDisplayName("§d[VIP] " + e.getPlayer().getName());
            p.setPlayerListName("§d[VIP] " + p.getName());

            p.setAllowFlight(true);
            membro = false;
        }
        if (p.hasPermission("n3rdydev.cargo.admin")){
            p.setDisplayName("§c[Admin] " + p.getName());
            p.setPlayerListName("§c[Admin] " + p.getName());
            p.setAllowFlight(true);
        }
        if(p.hasPermission("n3rdydev.cargo.developer")){
            p.setDisplayName("§6[Dev] " + p.getName());
            p.setPlayerListName("§6[Dev] " + p.getName());
            p.setAllowFlight(true);
        }
        if(membro != false){
            e.setJoinMessage("");
        }

        Location loc = new Location(p.getWorld(), spawn.spawn_x, spawn.spawn_y, spawn.spawn_z);
        p.teleport(loc);
        p.getInventory().clear();

        ItemStack bussola = new ItemStack(Material.COMPASS, 1);
        ItemMeta bussola_meta = bussola.getItemMeta();
        bussola_meta.setDisplayName("§f§b§eServidores§f §7(Clique para Selecionar)");
        bussola_meta.setLore(Arrays.asList("Lista de Servidores","Clique em um para entrar"));
        bussola.setItemMeta(bussola_meta);
        p.getInventory().setItem(4, bussola);


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        e.setQuitMessage("");
    }
    @EventHandler
    public void onPlayerDie(PlayerDeathEvent e){
        e.setDeathMessage("");
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL))){
            e.setDamage(0.0);
        }
    }


    @EventHandler
    public void PlayerHunger(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void PlayerBreakBlock(BlockBreakEvent e){
        if(e.getPlayer().hasPermission("n3rdydev.0000.buildon")){
            e.setCancelled(false);
        }
        else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerPlaceBlock(BlockPlaceEvent e){
        if(e.getPlayer().hasPermission("n3rdydev.0000.buildon")){
            e.setCancelled(false);
        }
        else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerInteractEvent (PlayerInteractEvent e){
        if(e.getPlayer().hasPermission("n3rdydev.0000.buildon")){
            e.setCancelled(false);
        }
        else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void WeatherChange (WeatherChangeEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getPlayer().getLocation().getY() < 0) {
            Location loc = new Location(e.getPlayer().getWorld(), spawn.spawn_x, spawn.spawn_y, spawn.spawn_z);
            e.getPlayer().teleport(loc);
        }
    }

    @EventHandler
    public void OnPlayerPvP(EntityDamageEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void OnPlayerMove(PlayerChatEvent e){
        String cargo = "§7";
        if(e.getPlayer().hasPermission("n3rdydev.cargo.vip")){
            cargo = "§d[VIP]§f ";
        }

        if(e.getPlayer().isOp() != false){
            cargo = "§c[ADMIN]§f ";
        }

        e.setFormat(cargo + e.getPlayer().getName() + ": " + e.getMessage());
    }

    @EventHandler
    public void OnTntExplode(EntityDamageEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void OnEntityExplode(EntityExplodeEvent e){
        e.setCancelled(true);
    }

}
