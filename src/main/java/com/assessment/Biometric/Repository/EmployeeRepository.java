package com.assessment.Biometric.Repository;

import com.assessment.Biometric.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("select e from Employee e where e.key=:key")
    Optional<Employee> findEmployeeByKey(@Param("key") String key);
}
