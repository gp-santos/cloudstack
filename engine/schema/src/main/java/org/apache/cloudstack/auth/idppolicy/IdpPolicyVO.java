// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements. See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership. The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License. You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied. See the License for the
// specific language governing permissions and limitations
// under the License.
package org.apache.cloudstack.auth.idppolicy;

import com.cloud.utils.db.GenericDao;
import org.apache.cloudstack.utils.reflectiontostringbuilderutils.ReflectionToStringBuilderUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "idp_policy")
public class IdpPolicyVO implements IdpPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "uuid", nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "idp_id", nullable = false)
    private String idpId;

    @Column(name = "useraccount_op", nullable = false)
    @Enumerated(EnumType.STRING)
    private Operation userAccountOperation = Operation.NONE;

    @Column(name = "domain_op", nullable = false)
    @Enumerated(EnumType.STRING)
    private Operation domainOperation = Operation.NONE;

    @Column(name = "role_op", nullable = false)
    @Enumerated(EnumType.STRING)
    private Operation roleOperation = Operation.NONE;

    @Column(name = "bindings", nullable = false)
    private String bindings;

    @Column(name = "description")
    private String description;

    @Column(name = GenericDao.CREATED_COLUMN, nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = GenericDao.REMOVED_COLUMN)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date removed;

    public IdpPolicyVO() {
    }

    public IdpPolicyVO(String idpId, Operation userAccountOperation, Operation domainOperation, Operation roleOperation, String bindings, String description) {
        this.idpId = idpId;
        this.userAccountOperation = userAccountOperation;
        this.domainOperation = domainOperation;
        this.roleOperation = roleOperation;
        this.bindings = bindings;
        this.description = description;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilderUtils.reflectOnlySelectedFields(this, "uuid", "idpId", "useraccount_operation", "domain_operation", "role_operation",
                "description");
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getIdpId() {
        return idpId;
    }

    @Override
    public Operation getUserAccountOperation() {
        return userAccountOperation;
    }

    @Override
    public Operation getDomainOperation() {
        return domainOperation;
    }

    @Override
    public Operation getRoleOperation() {
        return roleOperation;
    }

    @Override
    public String getBindings() {
        return bindings;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public Date getRemoved() {
        return removed;
    }

    @Override
    public long getId() {
        return id;
    }
}
