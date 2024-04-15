package com.nyanmyohtet.studentregistrationservice.service.Impl;
import com.nyanmyohtet.studentregistrationservice.service.MultitenantDataSourceProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class MultitenantDataSourceProviderImpl implements MultitenantDataSourceProvider {
    private final Map<String, DataSource> dataSourceMap = new HashMap<>();

    public void addDataSource(String tenantId, DataSource dataSource) {
        dataSourceMap.put(tenantId, dataSource);
    }

    @Override
    public DataSource getDataSourceForTenant(String tenantId) {
        return dataSourceMap.get(tenantId);
    }
}
