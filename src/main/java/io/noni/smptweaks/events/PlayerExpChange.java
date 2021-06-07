package io.noni.smptweaks.events;

import io.noni.smptweaks.SMPtweaks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerExpChange implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerExpChange(PlayerExpChangeEvent e) {
        // Apply xp multiplier
        double xpMultiplier = SMPtweaks.getCfg().getDouble("xp_multiplier");
        int amount = (int) Math.max(1, Math.round(e.getAmount() * xpMultiplier));
        e.setAmount(amount);
    }
}
