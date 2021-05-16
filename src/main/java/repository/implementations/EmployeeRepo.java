package repository.implementations;

import domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.interfaces.EmployeeRepository;
import repository.interfaces.JdbcUtils;

import java.util.List;

public class EmployeeRepo implements EmployeeRepository {

    SessionFactory sessionFactory;

    public EmployeeRepo() {
        sessionFactory = JdbcUtils.getSessionFactory();
    }

    @Override
    public Employee findByUsername(String username) {
        System.out.println(username);
        Employee result = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                result = session.createQuery("from Employee where username = ?1", Employee.class)
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

    @Override
    public Employee findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Employee> findAll() {
        return null;
    }

    @Override
    public Employee save(Employee entity) {
        return null;
    }

    @Override
    public Employee delete(Employee employee) {
        return null;
    }


    @Override
    public Employee update(Employee entity) {
        return null;
    }
}
