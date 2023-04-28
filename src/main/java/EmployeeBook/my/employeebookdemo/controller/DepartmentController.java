package EmployeeBook.my.employeebookdemo.controller;

import EmployeeBook.my.employeebookdemo.Employee;
import EmployeeBook.my.employeebookdemo.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/departments")
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping(path = "/min-salary")
    public Employee getEmployeeWithMinSalaryInSpecificDepartment(@RequestParam(value = "department", required = false) int department) {
        return departmentService.getEmployeeWithMinSalaryInSpecificDepartment(department);
    }
    @GetMapping(path = "/max-salary")
    public Employee getEmployeeWithMaxSalaryInSpecificDepartment(@RequestParam(value = "department", required = false) int department) {
        return departmentService.getEmployeeWithMaxSalaryInSpecificDepartment(department);
    }
    @GetMapping(path = "/all")
    public List<Employee> outputAllEmployeesInSpecificDepartment(@RequestParam(value = "department", required = false) int department) {
        return departmentService.outputAllEmployeesInSpecificDepartment(department);
    }@GetMapping(path = "/all/by-department")
    public Map<Integer, List<Employee>> outputAllEmployeesByDepartment() {
        return departmentService.outputAllEmployeesByDepartment();
    }
}
