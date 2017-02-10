package tacos.killcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().fine("\u00a7cPlugin created by Tacos!");
        
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        getServer().getPluginManager().registerEvents((Listener) this, (Plugin) this);
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player killer = p.getKiller();

        for (String s : getConfig().getStringList("commands")) {
            cmdExec(s.replace("<target>", p.getName()).replace("<killer>", killer.getName()));
        }
    }

    private void cmdExec(String s) {
        getServer().dispatchCommand(getServer().getConsoleSender(), s.replace("&", "\u00a7"));
    }
}
