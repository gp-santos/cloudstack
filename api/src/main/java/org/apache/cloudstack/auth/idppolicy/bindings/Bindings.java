package org.apache.cloudstack.auth.idppolicy.bindings;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Bindings {

    private UserBindings userBindings;
    private AccountBindings accountBindings;
    private DomainBindings domainBindings;
    private RoleBindings roleBindings;

    public Bindings() {}

    public UserBindings getUserBindings() {
        return userBindings;
    }

    public AccountBindings getAccountBindings() {
        return accountBindings;
    }

    public DomainBindings getDomainBindings() {
        return domainBindings;
    }

    public RoleBindings getRoleBindings() {
        return roleBindings;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
