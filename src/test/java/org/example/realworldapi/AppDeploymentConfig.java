package org.example.realworldapi;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

public class AppDeploymentConfig implements SharedContainerConfiguration {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0").withDatabaseName("postgres_db").withPassword("S3cret").withUsername("postgres_user").withNetworkAliases("testpostgres");

    @Container
    public static ApplicationContainer app = new ApplicationContainer().withHttpPort(9080)
            .withEnv("POSTGRESQL_HOSTNAME", "testpostgres").withEnv("POSTGRESQL_PORT", "5432").waitingFor(Wait.forHttp("/health/ready")).withAppContextRoot("/realworld-liberty");

}
