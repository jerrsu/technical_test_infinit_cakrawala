package com.technical.test.controller;

import com.technical.test.dto.EmployeeDto;
import com.technical.test.dto.Response;
import com.technical.test.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jerry
 */

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<?> getListEmployee() {
        List<EmployeeDto> employeeDto = employeeService.list();
        log.info("Employee list successfully");
        return new ResponseEntity<>(new Response<>("200","success", employeeDto), HttpStatus.OK);
   }

    @GetMapping("/page")
    public ResponseEntity<?> getPageEmployee(Pageable pageable) {
        Page<EmployeeDto> employeeDto = employeeService.page(pageable);
        log.info("Employee page successfully");
        return new ResponseEntity<>(new Response<>("200","success", employeeDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPageEmployee(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        if (employeeDto == null) {
            log.error("Employee with id {} not found", id);
            return new ResponseEntity<>(new Response<>("404","error", "not found"), HttpStatus.NOT_FOUND);
        }
        log.info("Employee with id {} found", employeeDto);
        return new ResponseEntity<>(new Response<>("200","success", employeeDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            EmployeeDto data = employeeService.save(employeeDto);
            log.info("Employee created successfully");
            return new ResponseEntity<>(new Response<>("201","success", data), HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Employee creation failed", e);
            return new ResponseEntity<>(new Response<>("500","error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update data employee
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeDto employeeDto) {
        EmployeeDto datalama = employeeService.findById(id);
        if (datalama == null) {
            log.error("Employee with id {} not found", id);
            return new ResponseEntity<>(new Response<>("404","error", "Employee not found"), HttpStatus.NOT_FOUND);
        }
        try {
            EmployeeDto data = employeeService.update(id,employeeDto);
            log.info("Employee updated successfully");
            return new ResponseEntity<>(new Response<>("200","success", data), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Employee update failed", e);
            return new ResponseEntity<>(new Response<>("500","error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        if (employeeDto == null) {
            log.error("Employee with id {} not found", id);
            return new ResponseEntity<>(new Response<>("404","error", "not found"), HttpStatus.NOT_FOUND);
        }
        employeeService.delete(id);
        log.info("Employee deleted successfully");
        return new ResponseEntity<>(new Response<>("200","success", "deleted"), HttpStatus.OK);

    }
}
