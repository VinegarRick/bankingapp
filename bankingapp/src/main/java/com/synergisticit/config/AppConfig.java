package com.synergisticit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.synergisticit.domain.User;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@PropertySource(value="classpath:db.properties")
public class AppConfig {
	@Autowired
	Environment env;
	
	@Bean
	public DriverManagerDataSource dataSource() {
		
		System.out.println("url: "+env.getProperty("url"));
		System.out.println("driver: "+env.getProperty("driver"));
		System.out.println("user name: "+env.getProperty("myusername"));
		System.out.println("password: "+env.getProperty("mypassword"));
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setDriverClassName(env.getProperty("driver"));
		dataSource.setUsername(env.getProperty("myusername"));
		dataSource.setPassword(env.getProperty("mypassword"));
		
		return dataSource;
	}
	
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		
		return jdbcTemplate;
	}
	
	//@Bean (name ="sessionFactory")
		@Bean //(name ="entityManagerFactory")
		public LocalSessionFactoryBean sessionFactory() {
			System.out.println("LocalSessionFactoryBean sessionFactory from AppConfig...");
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			sessionFactory.setDataSource(dataSource());
			sessionFactory.setAnnotatedClasses(User.class);
			sessionFactory.setPackagesToScan("com.synergisticit.domain");
			sessionFactory.setHibernateProperties(hibernateProperties());
			
			return sessionFactory;
		}
		
		@Primary
		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
			LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactory.setDataSource(dataSource());
			entityManagerFactory.setPackagesToScan("com.synergisticit.domain");
			entityManagerFactory.setJpaProperties(hibernateProperties());
			entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
			
			return entityManagerFactory;
		}

		Properties hibernateProperties() {
			Properties jpaProperties = new Properties();
			jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			jpaProperties.setProperty("hibernate.show_SQL", "true");
			//jpaProerties.setProperty("hibernate.hbm2ddl.auto", "create");
			jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			return jpaProperties;
		}
		
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("WEB-INF/message/messages");
		return messageSource;
	}
}
