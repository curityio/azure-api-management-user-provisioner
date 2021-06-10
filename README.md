# Azure API Management User Provisioner Authentication Action Plugin
[![Quality](https://img.shields.io/badge/quality-experiment-red)](https://curity.io/resources/code-examples/status/)
[![Availability](https://img.shields.io/badge/availability-source-blue)](https://curity.io/resources/code-examples/status/)

A custom authentication action plugin for the Curity Identity Server.

## System Requirements

The plugin is only tested with Curity Identity Server 6.2.0 but should work with older version also. More details in the [Systems Requirement](https://developer.curity.io/docs/latest/system-admin-guide/system-requirements.html) section of the product documentation.

## Building the Plugin

Build the plugin by issuing the command ``mvn package``. This will produce a JAR file in the ``target`` directory, which can be installed.

## Installing the Plugin with Dependencies

To install the plugin, copy the compiled JAR (and all of its dependencies from `target/dependency/`) into `${IDSVR_HOME}/usr/share/plugins/${pluginGroup}`
on each node, including the admin node. For more information about installing plugins, refer to the [Product Documentation](https://support.curity.io/docs/latest/developer-guide/plugins/index.html#plugin-installation).

## Configuration

Parameter | Description
------------------------|------------
Client ID | The ID of the client use dto connect to Azure. Can be obtained by creating a Service Provider (SP) with the command `az ad sp create-for-rbac`. The `appId` is to be used as the ClientId in this configuration.
Client Secret | Also available in the output of creating the SP. The Client Secret is the `password` in the output.
Tenant ID | Also available in the output of creating the SP. The Tenant Id is the `tenant` in the output.
Subscription ID | Can be obtained by running `az account list`. The output will contain an entry, `id`, this is the Subscription Id.
Resource Group Name | The name of the Resource Group that the API Management is configured in. This can be found in Azure -> API Management services. In the row for the given API Management service the name of the Resource Group is in the column `Resource group`.
Service Name | Similarly the Service Name can be found in the `Name` column.

![Configure Action](etc/config-azure-api-management-user-provisioner.png?raw=true "Configure Action")

## Required attributes
At a minimum the following attributes are required from the Authentication context:
- subject
- email
- attributes containing a name entry with givenName and familyName (`"attributes": "{'name': {'givenName': 'alice', 'familyName': 'anderson'}}"`)

## More Information

Please visit [curity.io](https://curity.io/) for more information about the Curity Identity Server.