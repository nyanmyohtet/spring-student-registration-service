package com.nyanmyohtet.studentregistrationservice.service;

import javax.sql.DataSource;

public interface MultitenantDataSourceProvider {
    DataSource getDataSourceForTenant(String tenantId);
}
