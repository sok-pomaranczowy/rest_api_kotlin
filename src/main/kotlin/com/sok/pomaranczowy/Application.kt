package com.sok.pomaranczowy

import com.sok.pomaranczowy.dba.EmployeeRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by sok_pomaranczowy on 27.11.16.
 */
@SpringBootApplication
//@ComponentScan(basePackages = arrayOf("com.sok.pomaranczowy.dba","com.sok.pomaranczowy.rest"))
//@Configuration
open class Application() {

/*
    @Bean
    open fun init(repository: EmployeeRepository) = CommandLineRunner{
    }
*/

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}