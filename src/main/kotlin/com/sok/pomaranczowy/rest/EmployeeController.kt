package com.sok.pomaranczowy.rest;

import com.sok.pomaranczowy.dba.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject


/**
 * Created by sok_pomaranczowy on 27.11.16.
 */
@RestController
@Component
class EmployeeController() {

    @Autowired
    constructor(employeeRepository: EmployeeRepository): this() {
        this.employeeRepository = employeeRepository
    }

    lateinit var employeeRepository: EmployeeRepository

    @RequestMapping("/")
    fun index(): String {
        return employeeRepository.findAll().map { it.toString() }.toString()
    }
}
