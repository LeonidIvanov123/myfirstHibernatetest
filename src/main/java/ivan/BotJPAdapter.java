package ivan;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class BotJPAdapter {
    private Session session = null;

    public BotJPAdapter() {
        SessionFactory sessionFactory  = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    /**
     * Процедура чтения записей
     */
    List<Botuser> recordsRead()
    {
        String query = "select p from " + Botuser.class.getSimpleName() + " p";
        @SuppressWarnings("unchecked")
        List<Botuser> list = (List<Botuser>)session.createQuery(query).list();
        return list;
    }

}
