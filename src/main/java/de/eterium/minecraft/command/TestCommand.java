package de.eterium.minecraft.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;

@CommandAlias("test")
public class TestCommand extends BaseCommand {

    @Default
    @Description("testcommand jaja")
    public void onTest(CommandSender sender) {
        sender.sendMessage("test");
    }

    @Subcommand("invite")
    @CommandAlias("testinvite")
    @Description("another test!")
    public void onTestMe(CommandSender sender, String target) {
        sender.sendMessage("test " + sender.getName());
        sender.sendMessage(target);
    }

}
