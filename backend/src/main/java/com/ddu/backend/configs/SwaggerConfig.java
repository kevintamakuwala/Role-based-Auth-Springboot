/*
 *  Author: Kevin Tamakuwala (21ITUBS120)
 *  Modified: 2nd March 2024 2:37 AM
 *  Purpose: This class is used to configure the swagger documentation for the application.
*/

package com.ddu.backend.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Kevin Tamakuwala",
                        email = "kevintamakuwala16@gmail.com",
                        url = "https://kevintamakuwala.netlify.app/"
                ),
                description = "API Documentation for Role Based Access Control System - By Kevin Tamakuwala.",
                title = "API specification",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8000"
                ),
                // @Server(
                //         description = "PROD ENV",
                //         url = "https://kevintamakuwala.com/"
                // )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}