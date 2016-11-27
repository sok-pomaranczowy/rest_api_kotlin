package com.sok.pomaranczowy.rest;

import com.sok.pomaranczowy.dba.Employees
import org.jetbrains.exposed.sql.selectAll
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Created by sok_pomaranczowy on 27.11.16.
 */
@RestController
class EmployeeController() {

    @RequestMapping("/")
    fun index() = Employees.selectAll();
}
