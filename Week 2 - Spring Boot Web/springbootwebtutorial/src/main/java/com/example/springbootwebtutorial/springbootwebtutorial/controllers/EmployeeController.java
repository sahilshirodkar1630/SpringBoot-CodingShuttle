package com.example.springbootwebtutorial.springbootwebtutorial.controllers;


import com.example.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.example.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/getSecretMessage")
    public String getMySuperSecretMessage(){
        return "Hi Sahil";
    }

    //PathVariable example
    @GetMapping(path = "/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    //RequestParam example
    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false,name="inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }
}
