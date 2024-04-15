package com.nyanmyohtet.studentregistrationservice.config;

import com.nyanmyohtet.studentregistrationservice.service.MultitenantDataSourceProvider;
import com.nyanmyohtet.studentregistrationservice.service.TenantResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(MultitenantDataSourceProvider dataSourceProvider, TenantResolver tenantResolver) {
        String tenantId = tenantResolver.resolveCurrentTenantId();
        return dataSourceProvider.getDataSourceForTenant(tenantId);
    }
}
