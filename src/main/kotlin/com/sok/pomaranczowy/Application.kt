package com.sok.pomaranczowy

//import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

/**
 * Created by sok_pomaranczowy on 27.11.16.
 */
@SpringBootApplication
@ComponentScan("com.sok.pomaranczowy")
@Configuration
@EnableTransactionManagement
open class Application(){

    @Bean
    open fun transactionManager(dataSource: DataSource) = DataSourceTransactionManager(dataSource)


    @Bean
    open fun persistenceExceptionTranslationPostProcessor() = PersistenceExceptionTranslationPostProcessor()

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}