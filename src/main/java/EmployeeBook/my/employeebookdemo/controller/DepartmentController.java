package EmployeeBook.my.employeebookdemo.controller;

import EmployeeBook.my.employeebookdemo.Employee;
import EmployeeBook.my.employeebookdemo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/department")
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> outputAllEmployeesInSpecificDepartment(@PathVariable int id) {
        return departmentService.outputAllEmployeesInSpecificDepartment(id);
    }
    @GetMapping(path = "/{id}/salary/sum")
    public double sumSalaryInSpecificDepartment(@PathVariable int id) {
        return departmentService.sumSalaryInSpecificDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/max")
    public double maxInSpecificDepartment(@PathVariable int id) {
        return departmentService.maxSalaryInSpecificDepartment(id);
    }
    @GetMapping(path = "/{id}/salary/min")
    public double minSalaryInSpecificDepartment(@PathVariable int id) {
        return departmentService.minSalaryInSpecificDepartment(id);
    }
    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> outputAllEmployeesByDepartment() {
        return departmentService.outputAllEmployeesByDepartment();
    }
}
