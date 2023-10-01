package de.eterium.core.data.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DataServiceContainer {
    private final CurrencyDataService currencyDataService;

}
