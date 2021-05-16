package repository.implementations;

import domain.Seat;
import domain.Show;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.interfaces.JdbcUtils;
import repository.interfaces.SeatRepository;

import java.util.List;

public class SeatRepo implements SeatRepository {
    SessionFactory sessionFactory;

    public SeatRepo() {
        sessionFactory = JdbcUtils.getSessionFactory();
    }

    @Override
    public Seat findOne(Integer integer) {
        Seat seat = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                seat = session.createQuery("from Seat where id = ?1", Seat.class)
                        .setParameter(1,integer).uniqueResult();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return seat;
    }

    @Override
    public Iterable<Seat> findAll() {
        return null;
    }

    @Override
    public Seat save(Seat entity) {
        return null;
    }

    @Override
    public Seat delete(Seat seat) {
        return null;
    }

    @Override
    public Seat update(Seat entity) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.update(entity);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return entity;
    }

    @Override
    public List<Seat> getFreeSeats(Show show) {
        int id = show.getId();
        List<Seat> seatList = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                seatList = session.createQuery("from Seat where show = ?1", Seat.class)
                        .setParameter(1,show).list();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return seatList;
    }
}
