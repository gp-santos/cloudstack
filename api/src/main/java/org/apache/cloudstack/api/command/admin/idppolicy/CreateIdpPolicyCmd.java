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
package org.apache.cloudstack.api.command.admin.idppolicy;

import com.cloud.exception.InvalidParameterValueException;
import com.cloud.exception.PermissionDeniedException;
import com.cloud.user.Account;
import org.apache.cloudstack.acl.RoleType;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiCommandResourceType;
import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.ApiErrorCode;
import org.apache.cloudstack.api.BaseCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.ServerApiException;
import org.apache.cloudstack.api.response.IdpPolicyResponse;
import org.apache.cloudstack.auth.idppolicy.IdpPolicy;
import org.apache.cloudstack.auth.idppolicy.IdpPolicyManager;
import org.apache.cloudstack.context.CallContext;

import javax.inject.Inject;

@APICommand(name = "createIdpPolicy", responseObject = IdpPolicyResponse.class, since = "4.20", entityType = IdpPolicyResponse.class, authorized = {RoleType.Admin},
        description = "Creates an IdP Policy to receive attributes from an Identity Provider.")
public class CreateIdpPolicyCmd extends BaseCmd {

    @Parameter(name= ApiConstants.IDP_ID, type = CommandType.STRING, length = 65535, required = true, description = "Entity ID of IdP which will send attributes.")
    private String idpId;

    @Parameter(name = ApiConstants.DESCRIPTION, type = CommandType.STRING, length = 65535, description = "Optional description for operator use.")
    private String description;

    @Parameter(name = ApiConstants.BINDINGS, type = CommandType.STRING, required = true, length = 65535, description = "")
    private String bindings;

    @Parameter(name = ApiConstants.USERACCOUNT_OPERATION, type = CommandType.STRING, description = "")
    private String userAccountOperation;

    @Parameter(name = ApiConstants.DOMAIN_OPERATION, type = CommandType.STRING, description = "")
    private String domainOperation;

    @Parameter(name = ApiConstants.ROLE_OPERATION, type = CommandType.STRING, description = "")
    private String roleOperation;

    @Inject
    private IdpPolicyManager idpPolicyManager;

    public String getIdpId() {
        return idpId;
    }
    public String getDescription() {
        return description;
    }
    public String getBindings() {
        return bindings;
    }

    public IdpPolicy.Operation getUserAccountOperation() throws InvalidParameterValueException {
        return getOperation(userAccountOperation, ApiConstants.USERACCOUNT_OPERATION);
    }

    public IdpPolicy.Operation getDomainOperation() throws InvalidParameterValueException {
        return getOperation(domainOperation, ApiConstants.DOMAIN_OPERATION);
    }

    public IdpPolicy.Operation getRoleOperation() throws InvalidParameterValueException {
        return getOperation(domainOperation, ApiConstants.ROLE_OPERATION);
    }

    private IdpPolicy.Operation getOperation(String operation, String apiConstant) {
        if (operation == null || operation.isBlank()) {
            return IdpPolicy.Operation.NONE;
        }
        try {
            return IdpPolicy.Operation.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Parameter [{}] does not accept [{}].", apiConstant, operation);
            throw new InvalidParameterValueException(String.format("Operation [%s] does not exist.", operation));
        }
    }

    @Override
    public void execute() throws ServerApiException {
        if (!_accountService.isRootAdmin(CallContext.current().getCallingAccountId())) {
            throw new PermissionDeniedException("You must be Root Admin to create an IdP Policy.");
        }

        IdpPolicy idpPolicy = idpPolicyManager.createIdpPolicy(this);
        if (idpPolicy == null) {
            throw new ServerApiException(ApiErrorCode.INTERNAL_ERROR, "Failed to create an IdP Policy.");
        }
        IdpPolicyResponse response = new IdpPolicyResponse(idpPolicy);
        response.setResponseName(getCommandName());
        this.setResponseObject(response);
    }

    @Override
    public long getEntityOwnerId() {
        return Account.ACCOUNT_ID_SYSTEM;
    }

    @Override
    public ApiCommandResourceType getApiResourceType() {
        return ApiCommandResourceType.IdpPolicy;
    }
}
