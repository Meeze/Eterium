package de.eterium.minecraft.command;

import co.aikar.commands.annotation.*;
import de.eterium.core.CoreAPI;
import de.eterium.core.data.controller.CurrencyController;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("money")
@Getter
@SuperBuilder
public class MoneyCommand extends EteriumCommand {

    private CurrencyController currencyController;

    @Default
    @Description("shows your money")
    @CommandCompletion("@players")
    public void onMoney(Player player, @Optional String targetName) {
        if(targetName == null) {
            CoreAPI.newChain().asyncFirst(() -> getCurrencyController().getMoney(player.getUniqueId())).syncLast(money -> sendMessage(player, "money_show_self", money)).execute();
        } else {
            UUID targetId = Bukkit.getPlayer(targetName).isOnline() ? Bukkit.getPlayer(targetName).getUniqueId() : Bukkit.getOfflinePlayer(targetName).getUniqueId();
            CoreAPI.newChain().asyncFirst(() -> getCurrencyController().getMoney(targetId)).syncLast(money -> sendMessage(player, "money_show_other", targetName, money)).execute();
        }
    }


}
