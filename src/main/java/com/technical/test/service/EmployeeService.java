package com.technical.test.service;

import com.technical.test.dto.EmployeeDto;
import com.technical.test.dto.mapper.EmployeeMapper;
import com.technical.test.model.Employee;
import com.technical.test.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jerry
 */

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    public List<EmployeeDto> list(){
        log.info("Proses get data...");
        return employeeRepository.findAll().stream().map(employeeMapper::toEmployeeDto).collect(Collectors.toList());
    }

    public Page<EmployeeDto> page(Pageable pageable){
        log.info("Proses get data page...");
        return employeeRepository.findAll(pageable).map(employeeMapper::toEmployeeDto);
    }

    public EmployeeDto findById(Long id){
        log.info("Proses get one data...");
        return employeeRepository.findById(id).map(employeeMapper::toEmployeeDto).orElse(null);
    }

    public EmployeeDto save(EmployeeDto employeeDto){
        log.info("Proses save data...");
        return employeeMapper.toEmployeeDto(employeeRepository.save(employeeMapper.toEmployee(employeeDto)));
    }

    public EmployeeDto update(Long id, EmployeeDto dataUpdate){
        log.info("Proses update data...");
        Employee dataLama = employeeRepository.findById(id).orElseThrow();
        Employee dataBaru = employeeMapper.toEmployee(dataUpdate);
        dataBaru = this.cekData(dataLama, dataBaru);
        employeeRepository.save(dataBaru);
        return employeeMapper.toEmployeeDto(dataBaru);
    }

    private Employee cekData(Employee dataLama, Employee dataBaru) {
        log.info("Proses cek data...");
        if (dataBaru.getName() != null && !dataBaru.getName().isBlank())
            dataLama.setName(dataBaru.getName());
        if (dataBaru.getDepartment() != null && !dataBaru.getDepartment().isBlank())
            dataLama.setDepartment(dataBaru.getDepartment());
        if (dataBaru.getJobTitle() != null && !dataBaru.getJobTitle().isBlank())
            dataLama.setJobTitle(dataBaru.getJobTitle());
        if (dataBaru.getSalary() != null)
            dataLama.setSalary(dataBaru.getSalary());
        if (dataBaru.getJoinDate() != null)
            dataLama.setJoinDate(dataBaru.getJoinDate());
        return dataLama;
    }

    public void delete(Long id){
        log.info("Proses delete data...");
        this.employeeRepository.deleteById(id);
    }
}
