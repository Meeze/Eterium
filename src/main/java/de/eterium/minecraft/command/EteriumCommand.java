package de.eterium.minecraft.command;

import co.aikar.commands.BaseCommand;
import de.eterium.core.game.service.MessengerGameService;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
public abstract class EteriumCommand extends BaseCommand {

    private MessengerGameService messengerGameService;

    void sendMessage(Player player, String messageKey, Object... replace) {
        getMessengerGameService().sendMessage(player, messageKey, replace);
    }

    UUID getIdFromOnlineOrOfflinePlayer(String targetName) {
        return Bukkit.getPlayer(targetName).isOnline() ? Bukkit.getPlayer(targetName).getUniqueId() : Bukkit.getOfflinePlayer(targetName).getUniqueId();
    }

}
