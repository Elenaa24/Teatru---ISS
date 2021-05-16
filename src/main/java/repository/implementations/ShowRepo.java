package repository.implementations;

import domain.Employee;
import domain.Show;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.interfaces.JdbcUtils;
import repository.interfaces.ShowRepository;

import java.util.List;

public class ShowRepo implements ShowRepository {
    SessionFactory sessionFactory;

    public ShowRepo() {
        sessionFactory = JdbcUtils.getSessionFactory();
    }

    @Override
    public Show findOne(Integer integer) {
        Show show = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                show = session.createQuery("from Show where id = ?1", Show.class)
                        .setParameter(1,integer).uniqueResult();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return show;
    }

    @Override
    public Iterable<Show> findAll() {
        List<Show> showList = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                showList = session.createQuery("from Show", Show.class)
                        .list();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return showList;
    }

    @Override
    public Show save(Show entity) {
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
    public Show delete(Show show) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.delete(show);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return show;
    }

    @Override
    public Show update(Show entity) {
        return null;
    }

}
