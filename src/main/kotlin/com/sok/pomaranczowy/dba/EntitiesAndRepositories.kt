package com.sok.pomaranczowy.dba


import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.validator.constraints.Length
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.Query;
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

        @Column(name = "birth_date")
        var birthDate: Date = Date(),

        @Column(name = "first_name")
        @Length(max = 14)
        var firstName: String = "",

        @Column(name = "last_name")
        @Length(max = 16)
        var lastName: String = "",

        @Column(name = "gender")
        @Enumerated(EnumType.STRING)
        var gender: Gender = Gender.M,

        @Column(name = "hire_date")
        var hireDate: Date = Date(),

        @JsonBackReference
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
        var salaries: List<Salary> = ArrayList<Salary>(),

        @JsonBackReference
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
        var titles: List<Title> = ArrayList<Title>()
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
        var titleCompositeKey: TitleCompositeKey = TitleCompositeKey(),

        @Column(name = "to_date")
        var endDate: Date = Date(),

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name = "emp_no", insertable = false, updatable = false)
        @JsonManagedReference
        var employee: Employee = Employee()
)
data class TitleCompositeKey(
        @Column(name = "emp_no", nullable = false)
        var employeeNumber: String = "",

        @Column(name = "title")
        var title: String = "",

        @Column(name = "from_date")
        var beginningDate: Date = Date()
) : Serializable

@Entity(name = "salaries")
data class Salary(
        @Id
        @Column(name = "emp_no", unique = true, nullable = false)
        var employeeNumber: Long = 0,

        @Column(name = "salary")
        var salary: Long = 0,

        @Column(name = "from_date")
        var fromDate: Date = Date(),

        @Column(name = "to_date")
        var toDate: Date = Date(),

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name = "emp_no", insertable = false, updatable = false)
        @JsonManagedReference
        var employee: Employee = Employee()

)// test no composite key on salary

@Entity(name = "dept_manager")
data class DepartmentManager(
        @Id
        @Column(name = "emp_no", unique = true, nullable = false)
        var employeeNumber: Long = 0,

        @Column(name = "dept_no", unique = true, nullable = false)
        var departmentNumber: Long = 0,

        @Column(name = "from_date")
        var fromDate: Date = Date(),

        @Column(name = "to_date")
        var toDate: Date = Date()
)

@Entity(name = "dept_emp")
data class DepartmentEmployee(
        @Id
        @Column(name = "emp_no", unique = true, nullable = false)
        var employeeNumber: Long = 0,

        @Column(name = "dept_no", unique = true, nullable = false)
        var departmentNumber: Long = 0,

        @Column(name = "from_date")
        var fromDate: Date = Date(),

        @Column(name = "to_date")
        var toDate: Date = Date()
)


enum class Gender {
    M, F
}

interface EmployeeRepository : CrudRepository<Employee, Long>
interface DepartmentRepository : CrudRepository<Department, Long> {
    @Query("SELECT d FROM departments d where d.departmentIdentifier = :id")
    fun findByDepartmentIdentifier(@Param("id")departmentIdentifier: String): Department
}

interface TitleRepository : CrudRepository<Title, Long>
interface SalaryRepository : CrudRepository<Salary, Long>
interface DepartmentManagerRepository : CrudRepository<DepartmentManager, Long>