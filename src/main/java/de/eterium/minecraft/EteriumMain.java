package de.eterium.minecraft;

import co.aikar.commands.PaperCommandManager;
import de.eterium.core.CoreAPI;
import de.eterium.core.game.service.MessengerGameService;
import de.eterium.minecraft.command.MoneyCommand;
import de.eterium.minecraft.command.TestCommand;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class EteriumMain extends JavaPlugin {

    private CoreAPI core = new CoreAPI();
    private PaperCommandManager paperCommandManager;

    @Override
    public void onEnable() {
        getCore().bootstrap();
        paperCommandManager = new PaperCommandManager(this);
        registerCommands();
    }

    private MessengerGameService getMessenger() {
        return core.getGameServiceContainer().getMessengerGameService();
    }

    private void registerCommands() {
        getPaperCommandManager().registerCommand(new TestCommand());
        getPaperCommandManager().registerCommand(MoneyCommand.builder().messengerGameService(getMessenger()).currencyController(core.getCurrencyController()).build());
    }

}
