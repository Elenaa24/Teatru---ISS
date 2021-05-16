package repository.implementations;

import domain.Employee;
import domain.Spectator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.interfaces.JdbcUtils;
import repository.interfaces.SpectatorRepository;

import java.util.List;

public class SpectatorRepo implements SpectatorRepository {
    SessionFactory sessionFactory;

    public SpectatorRepo() {
        sessionFactory = JdbcUtils.getSessionFactory();
    }

    @Override
    public Spectator findOne(Integer integer) {
        Spectator result = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                result = session.createQuery("from Spectator where id = ?1", Spectator.class)
                        .setParameter(1,integer).uniqueResult();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if(transaction !=null)
                    transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public Iterable<Spectator> findAll() {
        return null;
    }

    @Override
    public Spectator save(Spectator entity) {
        return null;
    }

    @Override
    public Spectator delete(Spectator spectator) {
        return null;
    }

    @Override
    public Spectator update(Spectator entity) {
        return null;
    }

    @Override
    public Spectator findByUsername(String username) {
        Spectator result = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                result = session.createQuery("from Spectator where username = ?1", Spectator.class)
                        .setParameter(1,username).uniqueResult();
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
