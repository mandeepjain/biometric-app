package com.assessment.Biometric.Controller;

import com.assessment.Biometric.Entity.Employee;
import com.assessment.Biometric.Repository.EmployeeRepository;
import com.assessment.Biometric.Service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/validate/{key}")
    public ResponseEntity<?> getAllBooks(@PathVariable String key) {
        if (employeeService.authenticateEmployee(key))
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.createMessage("Employee Authenticated"));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeService.createMessage("Authentication Failed"));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllEmployee() {
            return ResponseEntity.ok(employeeRepository.findAll());
    }


    @PostMapping(value = "/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        if (employeeService.authenticateEmployee(employee.getKey()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeService.createMessage("Employee Key already exists"));
        try {
            employeeRepository.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createMessage("Employee created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(employeeService.createMessage(e.getMessage()));
        }
    }

    @PutMapping(value = "/edit/{employeeId}")
    public ResponseEntity<?> editBook(@RequestBody Employee employee, @PathVariable long employeeId) {
        Optional<Employee> emp = employeeService.findEmployee(employeeId);
        if (emp.isPresent()) {
            employee.setId(employeeId);
            Employee existingEmp = emp.get();
            BeanUtils.copyProperties(employee, existingEmp);
            employeeRepository.save(existingEmp);
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.createMessage("Employee modified successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeService.createMessage("Employee with specified id:"+employeeId +" doesn't exist"));
    }

    @DeleteMapping(value = "/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable Long employeeId) {
        Optional<Employee> emp = employeeService.findEmployee(employeeId);
        if (emp.isPresent()) {
            try {
                employeeRepository.deleteById(employeeId);
                return ResponseEntity.status(HttpStatus.OK).body(employeeService.createMessage("Employee Deleted Successfully"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(employeeService.createMessage(e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeService.createMessage("Employee with specified id:"+employeeId +" doesn't exist"));

    }
}