package EmployeeBook.my.employeebookdemo.controller;

import EmployeeBook.my.employeebookdemo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RestController
public class EmployeeBookController {
    private final EmployeeService employeeService;

    public EmployeeBookController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                              @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return "Пожалуйста, заполните оба параметра!";
        }
        return "<strong> Добавлен сотрудник:<br><br>" + employeeService.addEmployee(firstName, lastName).toString() + "</strong>";
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return "Пожалуйста, заполните оба параметра!";
        }
        return "<strong> Удалён сотрудник:<br><br>" + employeeService.removeEmployee(firstName, lastName).toString() + "</strong>";
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                               @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return "Пожалуйста, заполните оба параметра!";
        }
        return "<strong> Найден сотрудник:<br><br>" + employeeService.findEmployee(firstName, lastName).toString() + "</strong>";
    }
}
