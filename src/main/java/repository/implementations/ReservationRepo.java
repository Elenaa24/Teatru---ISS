package repository.implementations;

import domain.Reservation;
import domain.Show;
import domain.Spectator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.interfaces.JdbcUtils;
import repository.interfaces.ReservationRepository;

import java.util.List;

public class ReservationRepo implements ReservationRepository {
    SessionFactory sessionFactory;

    public ReservationRepo() {
        sessionFactory = JdbcUtils.getSessionFactory();
    }

    @Override
    public Reservation findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Reservation> findAll() {
        List<Reservation> reservationList = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                reservationList = session.createQuery("from Reservation ", Reservation.class)
                        .list();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return reservationList;
    }

    @Override
    public Reservation save(Reservation entity) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.save(entity);
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
    public Reservation delete(Reservation reservation) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.delete(reservation);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return reservation;
    }

    @Override
    public Reservation update(Reservation entity) {
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
    public List<Reservation> getReservationsForOneSpectator(Spectator spectator) {
        List<Reservation> result = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                result = session.createQuery("from Reservation where spectator = ?1 ", Reservation.class).setParameter(1,spectator).list();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return result;
    }
}
