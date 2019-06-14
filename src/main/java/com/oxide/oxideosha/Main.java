package com.oxide.oxideosha;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (LivingEntity entity : getServer().getWorld("world").getLivingEntities()) {
                    if (entity.getLocation().subtract(0.0D, 0.4D, 0.0D).getBlock().getType().equals(Material.STONECUTTER)) {
                        entity.damage(1.0D);
                        if (entity instanceof Player) {
                            entity.sendMessage(ChatColor.RED + ("Ouch! ") + ChatColor.AQUA + ("You should be more careful " + ChatColor.GOLD + entity.getName()));
                        }
                    }
                }
            }
        }.runTaskTimer(this, 1L, 5L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
