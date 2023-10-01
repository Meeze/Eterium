package de.eterium.core.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class CurrencyPlayer extends DatabasePlayerEntity {

    public static CurrencyPlayer create(UUID uuid) {
        return CurrencyPlayer.builder().money(0).gems(0).BTC(0).id(uuid).build();
    }

    private long money;
    private long BTC;
    private long gems;

}
