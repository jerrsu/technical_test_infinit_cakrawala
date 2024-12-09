package com.technical.test.dto.mapper;

import com.technical.test.dto.EmployeeDto;
import com.technical.test.model.Employee;
import org.mapstruct.Mapper;

/**
 * @author jerry
 */

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    // proses mapping dari entity ke dto begitu juga sebaliknya

    EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeDto employeeDto);
}
