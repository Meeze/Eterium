package de.eterium.core.game.service;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MessengerGameService implements GameService {

    private static final String PLACEHOLDER = "%ph%";
    private Map<String, String> messages = new HashMap<>();

    public MessengerGameService() {
        getMessages().put("money_show_self", "Du hast %ph% Geld!");
        getMessages().put("money_show_other", "%ph% hat %ph% Geld!");
    }

    public void sendMessage(Player player, String messageKey, Object... replace) {
        player.sendMessage(getReplacedMessage(messageKey, replace));
    }

    public String getReplacedMessage(String messageKey, Object... replace) {
        String message = getMessages().get(messageKey);
        for (Object o : replace) {
            message = message.replaceFirst(PLACEHOLDER, o.toString());
        }
        return message;
    }



}
