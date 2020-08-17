package com.login.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

	@Configuration
	@EnableWebMvc
	@EnableTransactionManagement
	@ComponentScan("com.login.Login")
	@PropertySource(value = { "classpath:persistence-mysql.properties" })  
	@PropertySource(value = { "classpath:persistence-mysql.properties" })  
	@PropertySource(value = { "classpath:application.properties" }) 
	
	public class LoginConfig {
		@Autowired
		private Environment environment;
		
		@Bean
		public DataSource myDataSource(){
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
			
		}

		dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));  
        dataSource.setUser(environment.getProperty("jdbc.user"));  
        dataSource.setPassword(environment.getProperty("jdbc.password"));  
            
        dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));  
        dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));  
        dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));       
        dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));  
  
        return dataSource;  
		}
		
		private int getIntProperty(String propName) {  
            
            String propVal = environment.getProperty(propName);   
            int intPropVal = Integer.parseInt(propVal);  
            return intPropVal;  
        }  
		
		private Properties getHibernateProperties() {  
	        Properties props = new Properties();  
	        props.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));  
	        props.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));  
	        props.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));  
	        props.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl"));  
	        return props;                 
	    }  
		
		@Bean
		public LocalSessionFactoryBean factoryBean(){
			LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
			bean.setDataSource(myDataSource());
			bean.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));  
            bean.setHibernateProperties(getHibernateProperties());    
            return bean;  
		}
		
		@Bean
		@Autowired
		public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory);
			return transactionManager;
		}
}