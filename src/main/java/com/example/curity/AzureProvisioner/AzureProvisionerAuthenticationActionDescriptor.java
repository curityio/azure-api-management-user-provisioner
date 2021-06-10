package com.example.curity.AzureProvisioner;

import se.curity.identityserver.sdk.authenticationaction.AuthenticationAction;
import se.curity.identityserver.sdk.plugin.descriptor.AuthenticationActionPluginDescriptor;

public final class AzureProvisionerAuthenticationActionDescriptor implements AuthenticationActionPluginDescriptor<AzureProvisionerAuthenticationActionConfig>
{
    @Override
    public Class<? extends AuthenticationAction> getAuthenticationAction()
    {
        return AzureProvisionerAuthenticationAction.class;
    }

    @Override
    public String getPluginImplementationType()
    {
        return "Azure-API-Management-User-Provisioner";
    }

    @Override
    public Class<? extends AzureProvisionerAuthenticationActionConfig> getConfigurationType()
    {
        return AzureProvisionerAuthenticationActionConfig.class;
    }    
}
