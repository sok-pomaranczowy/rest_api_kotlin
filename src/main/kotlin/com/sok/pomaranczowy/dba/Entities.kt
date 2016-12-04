package com.sok.pomaranczowy.dba
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import au.com.console.jpaspecificationdsl.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.*

/**
 * Created by sok_pomaranczowy on 27.11.16.
 */
@Entity(name="employees")
data class Employee(
        @Id
        @GeneratedValue
        @Column(name="emp_no")
        var employeeNumber: Long = 0,
        @Column(name="birth_date")
        var birthDate: Date = Date(),
        @Column(name="first_name")
        var firstName: String = "",
        @Column(name="last_name")
        var lastName: String = "",
        @Column(name="gender")
        @Enumerated(EnumType.STRING)
        var gender: Gender = Gender.M,
        @Column(name="hire_date")
        var hireDate: Date = Date()) {

    override fun toString(): String {
        return "Entities(id=$employeeNumber, firstName='$firstName', lastName='$lastName' ('$gender'))"
    }
}

enum class Gender{
    M, F
}

interface EmployeeRepository : CrudRepository<Employee, Long>
