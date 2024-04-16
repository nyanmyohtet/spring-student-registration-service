package com.nyanmyohtet.studentregistrationservice.config;

import com.nyanmyohtet.studentregistrationservice.service.Impl.MultitenantDataSourceProviderImpl;
import com.nyanmyohtet.studentregistrationservice.service.Impl.ThreadLocalTenantResolver;
import com.nyanmyohtet.studentregistrationservice.service.MultitenantDataSourceProvider;
import com.nyanmyohtet.studentregistrationservice.service.TenantResolver;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.nyanmyohtet.studentregistrationservice.persistence.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class MultitenantConfig {

    public static final String MODEL_PACKAGE = "com.nyanmyohtet.studentregistrationservice.persistence.model";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.tenant1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.tenant2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            MultitenantDataSourceProvider dataSourceProvider,
            JpaVendorAdapter jpaVendorAdapter,
            @Value("${spring.jpa.properties.hibernate.dialect}") String dialect,
            ConfigurableListableBeanFactory beanFactory
    ) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceProvider.getDataSourceForTenant("tenant1"));
        em.setPackagesToScan(MODEL_PACKAGE);
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setJpaPropertyMap(properties);
        em.getJpaPropertyMap().put(AvailableSettings.BEAN_CONTAINER, new SpringBeanContainer(beanFactory));
        return em;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public MultitenantDataSourceProvider dataSourceProvider() {
        MultitenantDataSourceProviderImpl dataSourceProvider = new MultitenantDataSourceProviderImpl();
        dataSourceProvider.addDataSource("tenant1", dataSource1());
        dataSourceProvider.addDataSource("tenant2", dataSource2());
        return dataSourceProvider;
    }

    @Bean
    public TenantResolver tenantResolver() {
        return new ThreadLocalTenantResolver();
    }
}
