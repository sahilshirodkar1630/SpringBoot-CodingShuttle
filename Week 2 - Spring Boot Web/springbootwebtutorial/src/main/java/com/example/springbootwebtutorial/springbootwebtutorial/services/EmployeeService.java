package com.example.springbootwebtutorial.springbootwebtutorial.services;

import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.example.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.example.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.example.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
       /// EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
       //return modelMapper.map(employeeEntity,EmployeeDTO.class);

       return employeeRepository.findById(id)
               .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity saveEmployee = modelMapper.map(inputEmployee,EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(saveEmployee),EmployeeDTO.class);
    }



    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeId) {
        boolean exists = ifExistsByEmployeeID(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found for Id :"+ employeeId);
        EmployeeEntity updateEmployee = modelMapper.map(employeeDTO,EmployeeEntity.class);
        updateEmployee.setId(employeeId);

        return modelMapper.map(employeeRepository.save(updateEmployee),EmployeeDTO.class);
    }

    public boolean ifExistsByEmployeeID(Long employeeId){
         return employeeRepository.existsById(employeeId);
    }
    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = ifExistsByEmployeeID(employeeId);
        if(!exists) return false;
        employeeRepository.deleteById(employeeId);
        return true;

    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = ifExistsByEmployeeID(employeeId);
        if(!exists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        updates.forEach((field,value)-> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
