package repository.implementations;

import domain.Employee;
import repository.interfaces.EmployeeRepository;

public class EmployeeRepo implements EmployeeRepository {
//    static SessionFactory sessionFactory;
//
//    static void initialize() {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure()
//                .build();
//        try {
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        }
//        catch (Exception e) {
//            System.out.println("catch");
//            e.printStackTrace();
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//    }
//
//    public EmployeeRepo() {
//        initialize();
//    }

//    static void close() {
//        if (sessionFactory != null) sessionFactory.close();
//    }

    @Override
    public Employee findByUsername(String username) {
//        System.out.println(username);
//        List<Employee> result = null;
//        try(Session session = sessionFactory.openSession()){
//            Transaction transaction = null;
//            try{
//                transaction = session.beginTransaction();
//                result = session.createQuery("from Employee where username = ?1", Employee.class)
//                        .setParameter(1,username).list();
//                transaction.commit();
//            } catch (Exception e) {
//                e.printStackTrace();
//                if(transaction !=null)
//                    transaction.rollback();
//            }
//        }
//        return result.get(0);
        return null;
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
    public Employee delete(Integer integer) {
        return null;
    }


    @Override
    public Employee update(Employee entity) {
        return null;
    }
}
