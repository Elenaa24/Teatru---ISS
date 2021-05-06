import domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;

public class Main {
    private static SessionFactory factory;

    public static void main(String[] args) {

        try {
            SessionFactory mySessionFactory =
                    new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            Show show = new Show("A","A", LocalDateTime.now(), "a");
            show.setId(34);
            Employee employee = new Employee("Elena","elena","1");
            Spot spot = new Spot(1,2,"free", show);
            Spectator spectator = new Spectator("S","S","S");
            spectator.setId(53);
            Reservation reservation = new Reservation(3,spot,spectator);
            Session session = mySessionFactory.openSession();
            Transaction t = session.beginTransaction();
            session.save(spot);
            session.save(employee);
            session.save(reservation);
            t.commit();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }
}

