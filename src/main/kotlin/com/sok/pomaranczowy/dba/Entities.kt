package com.sok.pomaranczowy.dba

import com.sok.pomaranczowy.dba.helpers.Gender
import org.jetbrains.exposed.sql.Table
import java.util.*

/**
 * Created by sok_pomaranczowy on 27.11.16.
 */
data class Employee(
        var employeeNumber: Long = 0,
        var birthDate: Date = Date(),
        var firstName: String = "",
        var lastName: String = "",
        var gender: Gender = Gender.M,
        var hireDate: Date = Date()) {

    override fun toString(): String {
        return "Entities(id=$employeeNumber, firstName='$firstName', lastName='$lastName' ('$gender'))"
    }
}

object Employees : Table() {
    val employeeNumber = integer("emp_no").autoIncrement().primaryKey()
    var birthDate= date("birth_date")
    var firstName = varchar("first_name",14)
    var lastName = varchar("last_name",16)
    var gender= enumeration("gender", Gender::class.java)
    var hireDate = date("hire_date")
}
