package com.xeoneux.caravansary.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.xeoneux.caravansary.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
