package com.sphinx.project.config.config_datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.fpt.fis.TGS.repo",
		entityManagerFactoryRef = "entityManagerFactoryFormManagement", transactionManagerRef = "transactionManagerFormManagement")
public class DatasourceDBMasterConfig {

	@Autowired
    private Environment env;

	@Bean(name = "datasourceFormManagement")
	public DataSource dataSourceJira() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(env.getProperty("spring.datasourceFormManagement.driverClassName"));
        dataSourceBuilder.url(env.getProperty("spring.datasourceFormManagement.url"));
        dataSourceBuilder.username(env.getProperty("spring.datasourceFormManagement.username"));
        dataSourceBuilder.password(env.getProperty("spring.datasourceFormManagement.password"));

        return dataSourceBuilder.build();
	}

	@Bean(name = "entityManagerFactoryFormManagement")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryJira() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSourceJira());
		em.setPackagesToScan("com.fpt.fis.TGS.model");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean(name = "transactionManagerFormManagement")
	public PlatformTransactionManager transactionManagerJira() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryJira().getObject());
		return transactionManager;
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");

		return properties;
	}
}
