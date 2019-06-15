package com.oxide.oxideosha;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        plugin = this;

        String damageagainst = plugin.getConfig().getString("mob-or-player-damage");
        double damage = plugin.getConfig().getDouble("damage-value");

        new BukkitRunnable() {
            @Override
            public void run() {
                for (LivingEntity entity : getServer().getWorld("world").getLivingEntities()) {
                    if (entity.getLocation().subtract(0.0D, 0.4D, 0.0D).getBlock().getType().equals(Material.STONECUTTER)) {
                        if (damageagainst.equalsIgnoreCase("mob")) {
                            if (entity instanceof Mob) {
                                entity.damage(damage);
                            }
                        } else if (damageagainst.equalsIgnoreCase("player")) {
                            if (entity instanceof Player) {
                                if (((Player) entity).getPlayer().getGameMode() == GameMode.SURVIVAL) {
                                    entity.damage(damage);
                                    entity.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("damage-message").replaceAll("%player%", ((Player) entity).getPlayer().getName())));
                                }
                            }
                        } else if (damageagainst.equalsIgnoreCase("both")) {
                            entity.damage(damage);
                            if (entity instanceof Player) {
                                if (((Player) entity).getPlayer().getGameMode() == GameMode.SURVIVAL) {
                                    entity.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("damage-message").replaceAll("%player%", ((Player) entity).getPlayer().getName())));
                                }
                            }
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
