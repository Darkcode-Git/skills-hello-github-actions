package com.cooprkc.demo.controller;

import com.cooprkc.demo.model.EmployeeEntity;
import com.cooprkc.demo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class EmployeeMvcController {

    private final EmployeeService service;

    public EmployeeMvcController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "list-employees";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Long id) {
        if (id != null) {
            model.addAttribute("employee", service.getEmployeeById(id));
        } else {
            model.addAttribute("employee", new EmployeeEntity());
        }
        return "add-edit-employee";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) {
        service.deleteEmployeeById(id);
        model.addAttribute("employees", service.getAllEmployees());
        return "list-employees";
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateEmployee(@ModelAttribute("employee") EmployeeEntity employee) {
        service.createOrUpdateEmployee(employee);
        return "redirect:/";
    }
}
