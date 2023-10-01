package de.eterium.core;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import de.eterium.core.data.controller.CurrencyController;
import de.eterium.core.data.model.CurrencyPlayer;
import de.eterium.core.data.service.CurrencyDataService;
import de.eterium.core.data.service.DataServiceContainer;
import de.eterium.core.game.service.GameServiceContainer;
import de.eterium.core.game.service.MessengerGameService;
import de.eterium.minecraft.EteriumMain;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Getter
public class CoreAPI {

    private SessionFactory sessionFactory;
    private DataServiceContainer dataServiceContainer;
    private GameServiceContainer gameServiceContainer;

    private CurrencyController currencyController;
    private static TaskChainFactory taskChainFactory;

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public static <T> TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }

    public void bootstrap() {
        this.bootstrapHibernate();
        this.bootstrapServices();
        this.bootstrapControllers();
        this.taskChainFactory = BukkitTaskChainFactory.create(EteriumMain.getPlugin(EteriumMain.class));
    }

    public void bootstrapServices() {
        this.dataServiceContainer = DataServiceContainer.builder().
                currencyDataService(new CurrencyDataService(getSessionFactory())).
                build();
        this.gameServiceContainer = GameServiceContainer.builder().
                messengerGameService(new MessengerGameService())
                .build();
    }

    public void bootstrapControllers() {
        this.currencyController = new CurrencyController(getDataServiceContainer().getCurrencyDataService());
    }

    protected void bootstrapHibernate() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            MetadataSources sources = new MetadataSources(registry);
            sources.addAnnotatedClass(CurrencyPlayer.class);
            sessionFactory = sources.buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
