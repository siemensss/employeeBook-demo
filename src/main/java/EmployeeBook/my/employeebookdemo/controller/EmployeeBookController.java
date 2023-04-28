package EmployeeBook.my.employeebookdemo.controller;

import EmployeeBook.my.employeebookdemo.Employee;
import EmployeeBook.my.employeebookdemo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/employee")
@RestController
public class EmployeeBookController {
    private final EmployeeService employeeService;

    public EmployeeBookController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                @RequestParam(value = "lastName", required = false) String lastName,
                                @RequestParam(value = "salary", required = false) double salary,
                                @RequestParam(value = "department", required = false) int department) {

        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam(value = "salary", required = false) double salary,
                                   @RequestParam(value = "department", required = false) int department) {

        return employeeService.removeEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName,
                                 @RequestParam(value = "salary", required = false) double salary,
                                 @RequestParam(value = "department", required = false) int department) {
        return employeeService.findEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/list")
    public Map<Integer, Employee> outputAMap() {
        return employeeService.outputAMap();
    }
}
