package com.sok.pomaranczowy.dba

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import au.com.console.jpaspecificationdsl.*
import org.hibernate.validator.constraints.Length
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * Created by sok_pomaranczowy on 27.11.16.
 */
@Entity(name = "employees")
data class Employee(
        @Id
        @GeneratedValue
        @Column(name = "emp_no", unique = true, nullable = false)
        var employeeNumber: Long = 0,

        @Column(name = "birth_date", nullable = false)
        var birthDate: Date = Date(),

        @Column(name = "first_name", nullable = false)
        @Length(max = 14)
        var firstName: String = "",

        @Column(name = "last_name", nullable = false)
        @Length(max = 16)
        var lastName: String = "",

        @Column(name = "gender", nullable = false)
        @Enumerated(EnumType.STRING)
        var gender: Gender = Gender.M,

        @Column(name = "hire_date", nullable = false)
        var hireDate: Date = Date()
)

@Entity(name = "departments")
data class Department(
        @Id
        @Column(name = "dept_no", nullable = false)
        @Length(max = 4)
        var departmentIdentifier: String = "",

        @Column(name = "dept_name", unique = true, nullable = false)
        @Length(max = 40)
        var departmentName: String = ""
)

@Entity(name = "titles")
data class Title(
        @EmbeddedId
        var titleCompositeKey: TitleCompositeKey,
        @Column(name = "to_date")
        var endDate: Date
)

data class TitleCompositeKey(
        @Column(name = "emp_no", nullable = false)
        var employeeNumber:String="",
        @Column(name="title", nullable = false)
        var title:String="",
        @Column(name="from_date",nullable = false)
        var beginningDate:Date=Date()
)

enum class Gender {
    M, F
}

interface EmployeeRepository : CrudRepository<Employee, Long>