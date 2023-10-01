package de.eterium.core.data.controller;

import de.eterium.core.data.model.CurrencyPlayer;
import de.eterium.core.data.service.CurrencyDataService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyDataService currencyDataService;

    public long getMoney(UUID id) {
        CurrencyPlayer currencyPlayer = getCurrencyDataService().getOrCreate(id);
        return currencyPlayer.getMoney();
    }

    public void setMoney(UUID id, long amount) {
        CurrencyPlayer currencyPlayer = getCurrencyDataService().getOrCreate(id);
        currencyPlayer.setMoney(amount);
        getCurrencyDataService().saveOrUpdate(currencyPlayer);
    }

    public void addMoney(UUID id, long amount) {
        this.setMoney(id, getMoney(id) + amount);
    }

    public void removeMoney(UUID id, long amount) {
        this.setMoney(id, getMoney(id) - amount);
    }

}
