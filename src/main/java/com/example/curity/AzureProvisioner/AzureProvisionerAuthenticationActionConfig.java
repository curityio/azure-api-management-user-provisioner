package com.example.curity.AzureProvisioner;

import se.curity.identityserver.sdk.config.Configuration;
import se.curity.identityserver.sdk.config.annotation.DefaultString;
import se.curity.identityserver.sdk.config.annotation.Description;

public interface AzureProvisionerAuthenticationActionConfig extends Configuration
{
    @Description("The Client Id used to authenticate to Azure.")
    String getClientId();

    @Description("The Client Secret used to authenticate to Azure.")
    String getClientSecret();

    @Description("The Azure Tenant Id that the API Management Service belongs to.")
    String getTenantId();

    @Description("The Azure Subscription Id that the API Management Service belongs to.")
    String getSubscriptionId();

    @Description("The Azure Resource Group Name that the API Management Service belongs to.")
    String getResourceGroupName();

    @Description("The name of the Azure API Management Service to create users in.")
    String getServiceName();

}
