package org.mobkiler.mobkiler;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public final class Mobkiler extends JavaPlugin implements Listener {

    private final HashMap<UUID, Entity> spawnedMobs = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("MobKiler enabled! Monsters will go BOOM!");
    }

    @Override
    public void onDisable() {
        spawnedMobs.clear();
        getLogger().info("MobKiler disabled!");
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();

        // فقط ماب‌های Monster
        if (!(entity instanceof Monster)) {
            return;
        }

        // ذخیره در هش مپ
        spawnedMobs.put(entity.getUniqueId(), entity);

        BukkitScheduler scheduler = getServer().getScheduler();

        // 4 ثانیه بعد (80 تیک) پرتاب به هوا
        scheduler.runTaskLater(this, () -> {
            if (!entity.isValid() || entity.isDead()) {
                spawnedMobs.remove(entity.getUniqueId());
                return;
            }

            // پرتاب به هوا
            Vector velocity = entity.getVelocity();
            velocity.setY(velocity.getY() + 2.0);
            entity.setVelocity(velocity);

            // 1 ثانیه بعد انفجار (20 تیک)
            scheduler.runTaskLater(this, () -> {
                if (!entity.isValid() || entity.isDead()) {
                    spawnedMobs.remove(entity.getUniqueId());
                    return;
                }

                Location loc = entity.getLocation();
                World world = entity.getWorld();

                // حذف ماب
                entity.remove();
                spawnedMobs.remove(entity.getUniqueId());

                // انفجار!
                world.createExplosion(loc, 4.0f, false, false);

            }, 20L);

        }, 80L);
    }
}
