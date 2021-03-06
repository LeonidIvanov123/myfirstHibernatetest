package ivan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.sql.*;

public class MyTestClass {

	//private Session session = null;
	
	public static void main(String[] args) throws SQLException {
		System.out.println("start app");
        //testJPA();

        //Connection dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhibernate", "hiberuser", "991_Roter");
        //System.out.println(dbCon.isReadOnly());

        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //Session session = sessionFactory.openSession();


        BotJPAdapter jpAdapter = new BotJPAdapter();
        List<Botuser> msg = new ArrayList<Botuser>();
        msg = jpAdapter.recordsRead();
        msg.stream().limit(5).forEach(System.out::println);



	}


    public static void testJPA(){
        /*
         * Maven качает новую версию jdbc. Для работы необходимо поменять в hibernate.cfg.xml
         * на строку с '.cj.' :
         * <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
         */
        //проверка доступности БД из кода напрямую без hibernate
        //Connection dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhibernate", "hiberuser", "991_Roter");
        //System.out.println(dbCon.isReadOnly());


        MyJPAdapter adapter = new MyJPAdapter();

        System.out.println("Добавим записи в БД");
        Person p = new Person(66, "Leo");
        adapter.recordsAdd(p);
        Person p1 = new Person(67,"Sen");
        adapter.recordsAdd(p1);

        System.out.println("Прочтем все записи:");
        List<Person> persons = new ArrayList<Person>();
        persons = adapter.recordsRead();
        System.out.println(persons);


        System.out.println("Ищем id=3");
        System.out.println(adapter.recordFind(3));
    }
}



class MyJPAdapter{	
	
	private Session session = null;
	
	public MyJPAdapter() {
		session = createHibernateSession();
	}
	
	private Session createHibernateSession()
    {
        SessionFactory   sessionFactory  = null;
        ServiceRegistry  serviceRegistry = null;
        //Session session = null;
        
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        /*
        try {
            try {
                Configuration cfg = new Configuration().
                                        addResource("person.hbm.xml").configure();
                serviceRegistry = new StandardServiceRegistryBuilder().
                                      applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
            session = sessionFactory.openSession();
            System.out.println("Создание сессии");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        */
        return session;
    }
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Процедура добавления записей в таблицу
     */
    void recordsAdd(Person person)
    {
        try {
            Transaction tx = session.beginTransaction();
            session.save(person);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Процедура чтения записей
     */
    List<Person> recordsRead()
    {
        String query = "select p from " + Person.class.getSimpleName() + " p";
        @SuppressWarnings("unchecked")
        List<Person> list = (List<Person>)session.createQuery(query).list();
        return list;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Процедура поиска записи
     */
    Person recordFind(final int id)
    {
        Person person = (Person) session.load(Person.class, id);
        return person;
    }
}
