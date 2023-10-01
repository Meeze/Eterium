package de.eterium.core.data.service;

import de.eterium.core.data.model.CurrencyPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class CurrencyDataService implements DataService<CurrencyPlayer> {

    private final SessionFactory sessionFactory;

    @Override
    public CurrencyPlayer get(UUID id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        CurrencyPlayer found = session.byId(CurrencyPlayer.class).load(id);
        tx.commit();
        session.close();
        return found;
    }

    @Override
    public CurrencyPlayer getOrCreate(UUID id) {
        CurrencyPlayer currencyPlayer = get(id);
        if(currencyPlayer != null) {
            return currencyPlayer;
        } else {
            CurrencyPlayer newPlayer = CurrencyPlayer.create(id);
            saveOrUpdate(newPlayer);
            return newPlayer;
        }
    }

    @Override
    public void saveOrUpdate(CurrencyPlayer entity) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(CurrencyPlayer entity) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void saveAll(List<CurrencyPlayer> entities) {
        entities.forEach(this::saveOrUpdate);
    }

}
