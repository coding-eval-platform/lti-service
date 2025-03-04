package ar.edu.itba.cep.lti_service.models;

import com.github.javafaker.Faker;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * Test class for {@link ToolDeployment}.
 */
class ToolDeploymentTest {


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests the creation of a {@link ToolDeployment} instance.
     */
    @Test
    void testCreation() throws NoSuchAlgorithmException {
        final var deploymentId = deploymentId();
        final var clientId = clientId();
        final var issuer = issuer();
        final var oidcAuthenticationEndpoint = oidcAuthenticationEndpoint();
        final var jwksEndpoint = jwksEndpoint();
        final var algorithm = signatureAlgorithm();
        final var privateKey = privateKey(algorithm);
        final var applicationKey = applicationKey();
        final var applicationSecret = applicationSecret();

        final var toolDeployment = new ToolDeployment(
                deploymentId,
                clientId,
                issuer,
                oidcAuthenticationEndpoint,
                jwksEndpoint,
                privateKey,
                algorithm,
                applicationKey,
                applicationSecret
        );

        Assertions.assertAll(
                "Creating a ToolDeployment is not working as expected",
                () -> Assertions.assertEquals(deploymentId, toolDeployment.getDeploymentId(), "Deployment id does not match"),
                () -> Assertions.assertEquals(clientId, toolDeployment.getClientId(), "Client id does not match"),
                () -> Assertions.assertEquals(issuer, toolDeployment.getIssuer(), "Issuer does not match"),
                () -> Assertions.assertEquals(
                        oidcAuthenticationEndpoint,
                        toolDeployment.getOidcAuthenticationEndpoint(),
                        "The Open-Id Connect authentication endpoint does not match"
                ),
                () -> Assertions.assertEquals(
                        jwksEndpoint,
                        toolDeployment.getJwksEndpoint(),
                        "The JWKS endpoint does not match"
                ),
                () -> Assertions.assertEquals(privateKey, toolDeployment.getPrivateKey(), "Private key does not match"),
                () -> Assertions.assertEquals(
                        algorithm,
                        toolDeployment.getSignatureAlgorithm(),
                        "Signature algorithm does not match"
                ),
                () -> Assertions.assertEquals(
                        applicationKey,
                        toolDeployment.getApplicationKey(),
                        "Application key does not match"
                ),
                () -> Assertions.assertEquals(
                        applicationSecret,
                        toolDeployment.getApplicationSecret(),
                        "Application secret does not match"
                )
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that a null deployment id is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullDeploymentId() {
        final var algorithm = signatureAlgorithm();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        null,
                        clientId(),
                        issuer(),
                        oidcAuthenticationEndpoint(),
                        jwksEndpoint(),
                        privateKey(algorithm),
                        algorithm,
                        applicationKey(),
                        applicationSecret()
                ),
                "Creating a ToolDeployment with a null deployment id is being allowed"
        );
    }

    /**
     * Tests that a null client id is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullClientId() {
        final var algorithm = signatureAlgorithm();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        deploymentId(),
                        null,
                        issuer(),
                        oidcAuthenticationEndpoint(),
                        jwksEndpoint(),
                        privateKey(algorithm),
                        algorithm,
                        applicationKey(),
                        applicationSecret()
                ),
                "Creating a ToolDeployment with a null client id is being allowed"
        );
    }

    /**
     * Tests that a null issuer is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullIssuer() {
        final var algorithm = signatureAlgorithm();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        deploymentId(),
                        clientId(),
                        null,
                        oidcAuthenticationEndpoint(),
                        jwksEndpoint(),
                        privateKey(algorithm),
                        algorithm,
                        applicationKey(),
                        applicationSecret()
                ),
                "Creating a ToolDeployment with a null issuer is being allowed"
        );
    }

    /**
     * Tests that a null Open-Id Connect authentication endpoint is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullOidcAuthenticationEndpoint() {
        final var algorithm = signatureAlgorithm();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        deploymentId(),
                        clientId(),
                        issuer(),
                        null,
                        jwksEndpoint(),
                        privateKey(algorithm),
                        algorithm,
                        applicationKey(),
                        applicationSecret()
                ),
                "Creating a ToolDeployment with a null Open-Id Connection authentication endpoint id is being allowed"
        );
    }

    /**
     * Tests that a null JWKS endpoint is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullJwksEndpoint() {
        final var algorithm = signatureAlgorithm();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        deploymentId(),
                        clientId(),
                        issuer(),
                        oidcAuthenticationEndpoint(),
                        null,
                        privateKey(algorithm),
                        algorithm,
                        applicationKey(),
                        applicationSecret()
                ),
                "Creating a ToolDeployment with a null JWKS endpoint is being allowed"
        );
    }

    /**
     * Tests that a null private key is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullPrivateKey() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        deploymentId(),
                        clientId(),
                        issuer(),
                        oidcAuthenticationEndpoint(),
                        jwksEndpoint(),
                        null,
                        signatureAlgorithm(),
                        applicationKey(),
                        applicationSecret()
                ),
                "Creating a ToolDeployment with a null Private key is being allowed"
        );
    }

    /**
     * Tests that a null {@link SignatureAlgorithm} is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullSignatureAlgorithm() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        deploymentId(),
                        clientId(),
                        issuer(),
                        oidcAuthenticationEndpoint(),
                        jwksEndpoint(),
                        privateKey(SignatureAlgorithm.RS512),
                        null,
                        applicationKey(),
                        applicationSecret()
                ),
                "Creating a ToolDeployment with a null Signature algorithm is being allowed"
        );
    }

    /**
     * Tests that a null application key is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullApplicationKey() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        deploymentId(),
                        clientId(),
                        issuer(),
                        oidcAuthenticationEndpoint(),
                        jwksEndpoint(),
                        privateKey(SignatureAlgorithm.RS512),
                        signatureAlgorithm(),
                        null,
                        applicationSecret()
                ),
                "Creating a ToolDeployment with a null application key is being allowed"
        );
    }

    /**
     * Tests that a null application secret is not allowed when creating a {@link ToolDeployment}
     * (i.e throws an {@link IllegalArgumentException}).
     */
    @Test
    void testNullApplicationSecret() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ToolDeployment(
                        deploymentId(),
                        clientId(),
                        issuer(),
                        oidcAuthenticationEndpoint(),
                        jwksEndpoint(),
                        privateKey(SignatureAlgorithm.RS512),
                        signatureAlgorithm(),
                        applicationKey(),
                        null
                ),
                "Creating a ToolDeployment with a null application secret is being allowed"
        );
    }


    // ================================================================================================================
    // Helpers
    // ================================================================================================================

    /**
     * @return A valid deployment id.
     */
    private static String deploymentId() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid client id.
     */
    private static String clientId() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid issuer.
     */
    private static String issuer() {
        return "https://" + Faker.instance().internet().domainName();
    }

    /**
     * @return A valid Open-Id Connect authentication endpoint
     */
    private static String oidcAuthenticationEndpoint() {
        return "https://" + Faker.instance().internet().domainName() + "/oidc";
    }

    /**
     * @return A valid JWKS endpoint
     */
    private static String jwksEndpoint() {
        return "https://" + Faker.instance().internet().domainName() + "/jwks";
    }

    /**
     * @return A random {@link String} to be passed as a private key.
     */
    private static String privateKey(final SignatureAlgorithm algorithm) throws NoSuchAlgorithmException {
        final var generator = KeyPairGenerator.getInstance(algorithm.getFamilyName());
        generator.initialize(2048);
        final var pair = generator.generateKeyPair();
        final var encoded = pair.getPrivate().getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }

    /**
     * @return A {@link SignatureAlgorithm}.
     */
    private static SignatureAlgorithm signatureAlgorithm() {
        return SignatureAlgorithm.RS512;
    }

    /**
     * @return A valid application key.
     */
    private static String applicationKey() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid application secret.
     */
    private static String applicationSecret() {
        return UUID.randomUUID().toString();
    }
}
