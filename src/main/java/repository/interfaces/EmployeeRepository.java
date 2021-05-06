package repository.interfaces;

import domain.Employee;

public interface EmployeeRepository extends Repository<Integer, Employee> {
    Employee findByUsername(String username);
}
