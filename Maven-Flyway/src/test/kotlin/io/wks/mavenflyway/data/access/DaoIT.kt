package io.wks.mavenflyway.data.access

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer


@SpringBootTest
@ContextConfiguration(initializers = [PostgreSQLContainerContextInitializer::class])
class DaoIT

class PostgreSQLContainerContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        val dbContainer: MyPostgreSQLContainer by lazy {
            MyPostgreSQLContainer().apply {
                start()
            }
        }
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        TestPropertyValues.of(
            "spring.datasource.url=" + dbContainer.jdbcUrl,
            "spring.datasource.username=" + dbContainer.username,
            "spring.datasource.password=" + dbContainer.password
        ).applyTo(applicationContext.environment);
    }

}

class MyPostgreSQLContainer(dockerImageName: String = "postgres:15.2") :
    PostgreSQLContainer<MyPostgreSQLContainer>(dockerImageName)