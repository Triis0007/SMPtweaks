package io.noni.smptweaks.commands;

import io.noni.smptweaks.utils.ChatUtils;
import io.noni.smptweaks.utils.TranslationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhereisCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("whereis")) {

            if(args.length == 0) {
                return false;
            }

            final Player targetPlayer = Bukkit.getPlayer(args[0]);
            final Player player = (Player) sender;

            if(targetPlayer == null) {
                ChatUtils.negative(player, TranslationUtils.get("error-online-player-not-found"));
                return true;
            }

            Location targetPlayerLocation = targetPlayer.getLocation();
            String worldName = targetPlayerLocation.getWorld().getName();
            String worldString = beautifyWorldName(worldName);

            String x = "" + ChatColor.WHITE + targetPlayerLocation.getBlockX() + ChatColor.RESET;
            String y = "" + ChatColor.WHITE + targetPlayerLocation.getBlockY() + ChatColor.RESET;
            String z = "" + ChatColor.WHITE + targetPlayerLocation.getBlockZ() + ChatColor.RESET;

            ChatUtils.commandResponse(player, TranslationUtils.get("whereis-message", new String[]{
                    targetPlayer.getName(),
                    worldString,
                    x + " " + y + " " + z
            }));
            return true;
        }

        return false;
    }

    /**
     * Beautify world name
     * @param worldName raw world name (eg. world3_the_nether)
     * @return beautified world name (eg. Nether)
     */
    private String beautifyWorldName(String worldName) {
        String beautifiedWorldName;
        if(worldName.contains("nether")) {
            String completeText = TranslationUtils.get("whereis-in-nether");
            String partToColor = completeText.substring(completeText.lastIndexOf(" ") + 1);
            String remainingText = completeText.replace(partToColor, "");
            beautifiedWorldName = ChatColor.GOLD + remainingText + ChatColor.DARK_RED + partToColor + ChatColor.GOLD;
        } else if(worldName.contains("end")) {
            String completeText = TranslationUtils.get("whereis-in-end");
            String partToColor = completeText.substring(completeText.lastIndexOf(" ") + 1);
            String remainingText = completeText.replace(partToColor, "");
            beautifiedWorldName = ChatColor.GOLD + remainingText + ChatColor.DARK_PURPLE + partToColor + ChatColor.GOLD;
        } else {
            String completeText = TranslationUtils.get("whereis-in-overworld");
            String partToColor = completeText.substring(completeText.lastIndexOf(" ") + 1);
            String remainingText = completeText.replace(partToColor, "");
            beautifiedWorldName = ChatColor.GOLD + remainingText + ChatColor.GREEN + partToColor + ChatColor.GOLD;
        }
        return beautifiedWorldName;
    }
}
