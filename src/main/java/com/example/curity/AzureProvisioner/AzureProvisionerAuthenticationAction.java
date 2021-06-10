package com.example.curity.AzureProvisioner;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.apimanagement.implementation.ApiManagementManager;
import com.microsoft.azure.management.apimanagement.implementation.UserContractInner;
import com.microsoft.azure.management.apimanagement.implementation.UserCreateParametersInner;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.curity.identityserver.sdk.attribute.Attribute;
import se.curity.identityserver.sdk.attribute.AuthenticationAttributes;
import se.curity.identityserver.sdk.attribute.SubjectAttributes;
import se.curity.identityserver.sdk.authentication.AuthenticatedSessions;
import se.curity.identityserver.sdk.authenticationaction.AuthenticationAction;
import se.curity.identityserver.sdk.authenticationaction.AuthenticationActionResult;
import se.curity.identityserver.sdk.service.authenticationaction.AuthenticatorDescriptor;

public final class AzureProvisionerAuthenticationAction implements AuthenticationAction
{
    private final AzureProvisionerAuthenticationActionConfig _configuration;
    private final static Logger _logger = LoggerFactory.getLogger(AzureProvisionerAuthenticationAction.class);

    public AzureProvisionerAuthenticationAction(AzureProvisionerAuthenticationActionConfig configuration)
    {
        _configuration = configuration;
    }

    @Override
    public AuthenticationActionResult apply(AuthenticationAttributes authenticationAttributes,
                                            AuthenticatedSessions authenticatedSessions,
                                            String authenticationTransactionId,
                                            AuthenticatorDescriptor authenticatorDescriptor)
    {
        SubjectAttributes sa = authenticationAttributes.getSubjectAttributes();
        Attribute email = sa.get("email");

        JSONObject attributes = new JSONObject(sa.get("attributes").getValue().toString());
        JSONObject name = attributes.getJSONObject("name");
        String firstName = name.getString("givenName");
        String lastName = name.getString("familyName");

        try {
            ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(
                    _configuration.getClientId(), _configuration.getTenantId(), _configuration.getClientSecret(), AzureEnvironment.AZURE);

            ApiManagementManager apimanager = ApiManagementManager.configure().authenticate(credentials, _configuration.getSubscriptionId());

            UserContractInner contract = apimanager.inner().users().createOrUpdate(
                    _configuration.getResourceGroupName(),
                    _configuration.getServiceName(),
                    authenticationAttributes.getSubject(),
                    new UserCreateParametersInner().withEmail(email.getValue().toString()).withFirstName(firstName).withLastName(lastName).withNote("Created by Curity Azure-API-Management-User-Provisioner Authentication Action"));

        }
        catch (Exception e)
        {
            _logger.error(e.toString());
        }

        return AuthenticationActionResult.successfulResult(authenticationAttributes);
    }
}
