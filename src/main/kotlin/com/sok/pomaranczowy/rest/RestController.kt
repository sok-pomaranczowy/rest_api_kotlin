package com.sok.pomaranczowy.rest;

import com.sok.pomaranczowy.dba.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject


/**
 * Created by sok_pomaranczowy on 27.11.16.
 */
@RestController
class RestController {

    @Autowired
    lateinit var employeeRepository: EmployeeRepository
    @Autowired
    lateinit var departmentRepository: DepartmentRepository
    @Autowired
    lateinit var salaryRepository: SalaryRepository

    @RequestMapping("/employees")
    fun getAllEmployees(): MutableIterable<Employee>? {
        var dupa = employeeRepository.findAll()
        return dupa
    }

    @RequestMapping("/departments")
    fun getAllDepartments(): MutableIterable<Department>? {
        return departmentRepository.findAll()
    }

    @RequestMapping("/salaries")
    fun getAllSalaries(): MutableIterable<Salary>? {
        return return salaryRepository.findAll()
    }
}
