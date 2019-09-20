package com.assessment.Biometric.Service;

import com.assessment.Biometric.Entity.Employee;
import com.assessment.Biometric.Repository.EmployeeRepository;
import net.bytebuddy.matcher.ElementMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Boolean authenticateEmployee(String key){
        if(employeeRepository.findEmployeeByKey(key).isPresent())
            return true;
        return false;
    }

    public Map createMessage(String message){ return Collections.singletonMap("message",message); }

    public Optional<Employee> findEmployee(Long employeeId){
        return employeeRepository.findById(employeeId);
    }


    static void hello(){

    }
}

