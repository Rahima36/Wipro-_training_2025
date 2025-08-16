package com.wipro.appdemo.controller;

import com.wipro.appdemo.model.Employee;
import com.wipro.appdemo.model.EmployeeType;
import com.wipro.appdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee-list";
    }

    
    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            employee.ifPresent(e -> model.addAttribute("employee", e));
        } else {
            model.addAttribute("employee", new Employee());
        }
        model.addAttribute("employeeTypes", EmployeeType.values());
        return "employee-form";
    }

    
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

    
    @GetMapping("/search")
    public String searchEmployeeByName(@RequestParam String name, Model model) {
        model.addAttribute("employees", employeeService.searchEmployeesByName(name));
        return "employee-list";
    }
}
