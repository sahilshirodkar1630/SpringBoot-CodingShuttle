package com.example.springbootwebtutorial.springbootwebtutorial.controllers;


import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;

import com.example.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        EmployeeDTO employeeDTO = employeeService.findById(id);
        if(employeeDTO==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
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

    @PutMapping("/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeDTO,employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping("/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId){
        return employeeService.updatePartialEmployeeById(employeeId, updates);

    }
}
