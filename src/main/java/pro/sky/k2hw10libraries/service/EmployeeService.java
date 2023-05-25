package pro.sky.k2hw10libraries.service;

import org.springframework.stereotype.Service;
import pro.sky.k2hw10libraries.exception.EmployeeAlreadyAddedException;
import pro.sky.k2hw10libraries.exception.EmployeeNotFoundException;
import pro.sky.k2hw10libraries.exception.EmployeeStorageIsFullException;
import pro.sky.k2hw10libraries.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final int LIMIT = 10;

    private final List<Employee> employees = new ArrayList<>();

    private final ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    public Employee add(String name,
                        String surname,
                        int department,
                        int salary) {
        Employee employee = new Employee(
               validatorService.validateName(name) ,
                validatorService.validateName(surname),
                department,
                salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name,
                           String surname,
                           int department,
                           int salary) {
        Employee employee = new Employee(name, surname, department, salary);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name,
                         String surname,
                         int department,
                         int salary) {
        Employee employee = new Employee(name, surname, department, salary);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

}

