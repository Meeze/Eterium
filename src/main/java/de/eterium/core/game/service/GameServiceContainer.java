package de.eterium.core.game.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameServiceContainer {
    private final MessengerGameService messengerGameService;
}
