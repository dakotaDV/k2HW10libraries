package pro.sky.k2hw10libraries.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.k2hw10libraries.exception.IncorrectNameException;
import pro.sky.k2hw10libraries.exception.IncorrectSurnameException;
import pro.sky.k2hw10libraries.model.Employee;
import pro.sky.k2hw10libraries.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String name,
                        @RequestParam("lastName") String surname,
                        @RequestParam("departmentId") int department,
                        @RequestParam("salary") int salary) {
        return employeeService.add(name, surname, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String name,
                           @RequestParam("lastName") String surname,
                           @RequestParam("departmentId") int department,
                           @RequestParam("salary") int salary) {
        return employeeService.remove(name, surname, department, salary);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String name,
                         @RequestParam("lastName") String surname,
                         @RequestParam("departmentId") int department,
                         @RequestParam("salary") int salary) {
        return employeeService.find(name, surname, department, salary);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
    @ExceptionHandler({IncorrectNameException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectNameException(IncorrectNameException e){
        return "Некорректное имя '%s'.formatted()e.getMessage())";
    }
    @ExceptionHandler({IncorrectNameException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectSurNameException(IncorrectSurnameException e){
        return "Некорректная фамилия '%s'.formatted()e.getMessage())";
    }

}

