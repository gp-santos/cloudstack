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
package org.apache.cloudstack.auth.idppolicy.bindings;

import org.apache.cloudstack.utils.reflectiontostringbuilderutils.ReflectionToStringBuilderUtils;

import java.util.Map;

public class AccountBindings {
    private String accountname;
    private Map<String, String> details;
    private String networkdomain;
    private String uuid;

    public AccountBindings() {}

    public String getAccountName() {
        return accountname;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public String getNetworkDomain() {
        return networkdomain;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilderUtils.reflectOnlySelectedFields(this, "uuid", "accountname");
    }
}
