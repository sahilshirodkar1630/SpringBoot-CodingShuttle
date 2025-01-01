package com.example.springbootwebtutorial.springbootwebtutorial.controllers;


import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;

import com.example.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/getSecretMessage")
    public String getMySuperSecretMessage(){
        return "Hi Sahil";
    }

    //PathVariable example
    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id){
        return employeeService.findById(id);
    }

    //RequestParam example
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false,name="inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.findAll();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.save(inputEmployee);
    }
}
