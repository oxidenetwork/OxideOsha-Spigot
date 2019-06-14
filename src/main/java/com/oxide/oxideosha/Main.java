package com.oxide.oxideosha;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (LivingEntity entity : getServer().getWorld("world").getLivingEntities()) {
                    if (entity.getLocation().subtract(0.4D, 0.4D, 0.4D).getBlock().getType().equals(Material.STONECUTTER)) {
                        entity.damage(1.0D);
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
