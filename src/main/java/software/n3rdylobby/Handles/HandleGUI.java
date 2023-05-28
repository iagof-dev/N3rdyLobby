package software.n3rdylobby.Handles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HandleGUI implements Listener {

    public static Inventory Server_GUI(Player p){
        //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
        Inventory inv = Bukkit.createInventory(p, 9*3, "Lista de Servidores");

        //adicionar itens
        inv.setItem(11, createItem(new ItemStack(Material.GRASS), "&9SURVIVAL", "&aEntre em um ambiente desafiador e imersivo.", "&aExplore uma ampla variedade de biomas.", "§6Clique para entrar!"));
        return inv;
    }
    private static ItemStack createItem(ItemStack item, String nome, String ... descricao){

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nome));
        List<String> lores = new ArrayList<>();
        for (String s : descricao){
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    

}
