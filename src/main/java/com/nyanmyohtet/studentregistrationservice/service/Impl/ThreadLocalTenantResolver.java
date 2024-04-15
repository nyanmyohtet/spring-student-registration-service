package com.nyanmyohtet.studentregistrationservice.service.Impl;

import com.nyanmyohtet.studentregistrationservice.service.TenantResolver;

public class ThreadLocalTenantResolver implements TenantResolver {
    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(String tenantId) {
        currentTenant.set(tenantId);
    }

    @Override
    public String resolveCurrentTenantId() {
        return currentTenant.get();
    }
}
