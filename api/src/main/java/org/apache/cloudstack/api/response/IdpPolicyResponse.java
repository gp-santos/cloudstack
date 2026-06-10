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
package org.apache.cloudstack.api.response;

import com.cloud.serializer.Param;
import com.google.gson.annotations.SerializedName;
import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.BaseResponse;
import org.apache.cloudstack.api.EntityReference;
import org.apache.cloudstack.auth.idppolicy.IdpPolicy;

import java.util.Date;

@EntityReference(value = IdpPolicy.class)
public class IdpPolicyResponse extends BaseResponse {

    @SerializedName(ApiConstants.ID)
    @Param(description = "ID of the IdP Policy.")
    private String id;

    @SerializedName(ApiConstants.DESCRIPTION)
    @Param(description = "Description of the IdP Policy.")
    private String description;

    @SerializedName(ApiConstants.IDP_ID)
    @Param(description = "IdP ID of the IdP SynPolicy.")
    private String idpId;

    @SerializedName(ApiConstants.USERACCOUNT_OPERATION)
    @Param(description = "UserAccount Operation of the IdP Policy.")
    private String userAccountOperation;

    @SerializedName(ApiConstants.DOMAIN_OPERATION)
    @Param(description = "Domain Operation of the IdP Policy.")
    private String domainOperation;

    @SerializedName(ApiConstants.ROLE_OPERATION)
    @Param(description = "Role Operation of the IdP Policy.")
    private String roleOperation;

    @SerializedName(ApiConstants.BINDINGS)
    @Param(description = "Mapping script of the IdP Policy.")
    private String mapping;

    @SerializedName(ApiConstants.CREATED)
    @Param(description = "Date the IdP Policy was created.")
    private Date created;

    @SerializedName(ApiConstants.REMOVED)
    @Param(description = "Date the IdP Policy was deleted.")
    private Date removed;

    public IdpPolicyResponse(IdpPolicy idpPolicy) {
        this.id = idpPolicy.getUuid();
        this.description = idpPolicy.getDescription();
        this.idpId = idpPolicy.getIdpId();
        this.userAccountOperation = idpPolicy.getUserAccountOperation().toString();
        this.mapping = idpPolicy.getBindings();
        this.created = idpPolicy.getCreated();
        this.removed = idpPolicy.getRemoved();

        this.setObjectName("idppolicy");
    }
}
