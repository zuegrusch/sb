package utils;
import models.Children;
import models.Parent;
import models.Region;
import models.School;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(School.class);
                configuration.addAnnotatedClass(Region.class);
                configuration.addAnnotatedClass(Children.class);
                configuration.addAnnotatedClass(Parent.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Ошибка в getSessionFactory" + e);
            }
        }
        return sessionFactory;
    }
}
