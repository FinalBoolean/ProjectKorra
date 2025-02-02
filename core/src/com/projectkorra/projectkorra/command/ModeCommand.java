package com.projectkorra.projectkorra.command;

import com.projectkorra.projectkorra.object.AirBlastType;
import org.bukkit.ChatColor;
import com.projectkorra.projectkorra.BendingPlayer;
import java.util.List;
import org.bukkit.command.CommandSender;

public class ModeCommand extends PKCommand
{
    public ModeCommand() {
        super("mode", "/bending mode [type]", "Allows you to switch through different configs", new String[] { "mode", "m", "config" });
    }
    
    @Override
    public void execute(final CommandSender sender, final List<String> args) {
        final BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(sender.getName());
        if (bPlayer == null) {
            sender.sendMessage(ChatColor.RED + "You are not a BendingPlayer!");
            return;
        }
        if (args.size() != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /bending mode [type]");
            return;
        }
        final String typeInput = args.get(0).toUpperCase();
        try {
            final AirBlastType newType = AirBlastType.valueOf(typeInput);
            bPlayer.setAirBlastType(newType);
            bPlayer.saveAirBlastType();
            sender.sendMessage(ChatColor.GREEN + "Your config has been set to " + ChatColor.AQUA + newType.name() + ChatColor.GREEN + ".");
        }
        catch (final IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + "Invalid type! Available types are:");
            for (final AirBlastType type : AirBlastType.values()) {
                sender.sendMessage(ChatColor.YELLOW + "- " + type.name());
            }
        }
    }
}
