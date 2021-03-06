package react.analytics;

import com.google.inject.AbstractModule;
import org.apache.http.ssl.SSLContextBuilder;
import play.Environment;
import javax.annotation.Nonnull;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

/**
 * Guice module responsible for configuring & creating an instance of {@link AnalyticsService}.
 */
public class AnalyticsServiceModule extends AbstractModule {

    /*
        Required Config Paths
     */
    private static final String ELASTIC_CLIENT_HOST_PATH = "analytics.elastic.host";
    private static final String ELASTIC_CLIENT_PORT_PATH = "analytics.elastic.port";
    /*
        Optional Config Paths
     */
    private static final String ELASTIC_CLIENT_USE_SSL_PATH = "analytics.elastic.useSSL";
    private static final String ELASTIC_CLIENT_THREAD_COUNT_PATH = "analytics.elastic.threadCount";
    private static final String ELASTIC_CLIENT_CONNECTION_REQUEST_TIMEOUT_PATH = "analytics.elastic.connectionRequestTimeout";
    private static final String ELASTIC_CLIENT_USERNAME_PATH = "analytics.elastic.username";
    private static final String ELASTIC_CLIENT_PASSWORD_PATH = "analytics.elastic.password";

    /*
        Required SSL Config Paths
     */
    private static final String ELASTIC_CLIENT_SSL_PROTOCOL_PATH = "analytics.elastic.sslContext.sslProtocol";
    private static final String ELASTIC_CLIENT_SSL_SECURE_RANDOM_IMPL_PATH = "analytics.elastic.sslContext.sslSecureRandomImplementation";
    private static final String ELASTIC_CLIENT_SSL_TRUST_STORE_FILE_PATH = "analytics.elastic.sslContext.sslTrustStoreFile";
    private static final String ELASTIC_CLIENT_SSL_TRUST_STORE_TYPE_PATH = "analytics.elastic.sslContext.sslTrustStoreType";
    private static final String ELASTIC_CLIENT_SSL_TRUST_STORE_PASSWORD_PATH = "analytics.elastic.sslContext.sslTrustStorePassword";
    private static final String ELASTIC_CLIENT_SSL_KEY_STORE_FILE_PATH = "analytics.elastic.sslContext.sslKeyStoreFile";
    private static final String ELASTIC_CLIENT_SSL_KEY_STORE_TYPE_PATH = "analytics.elastic.sslContext.sslKeyStoreType";
    private static final String ELASTIC_CLIENT_SSL_KEY_STORE_PASSWORD_PATH = "analytics.elastic.sslContext.sslKeyStorePassword";

    /*
        Default values
     */
    private static final Integer DEFAULT_THREAD_COUNT = 1;
    private static final Integer DEFAULT_CONNECTION_TIMEOUT = 50;

    private final com.typesafe.config.Config _configs;

    public AnalyticsServiceModule(final Environment environment, final com.typesafe.config.Config configs) {
        _configs = configs;
    }

    @Override
    protected void configure() {
        final SSLContext context = createSSLContext();
        final AnalyticsService backend = new AnalyticsService(ElasticClientFactory.createElasticClient(
                _configs.hasPath(ELASTIC_CLIENT_USE_SSL_PATH) && _configs.getBoolean(ELASTIC_CLIENT_USE_SSL_PATH),
                _configs.getString(ELASTIC_CLIENT_HOST_PATH),
                _configs.getInt(ELASTIC_CLIENT_PORT_PATH),
                _configs.hasPath(ELASTIC_CLIENT_THREAD_COUNT_PATH) ? _configs.getInt(ELASTIC_CLIENT_THREAD_COUNT_PATH) : DEFAULT_THREAD_COUNT,
                _configs.hasPath(ELASTIC_CLIENT_CONNECTION_REQUEST_TIMEOUT_PATH) ? _configs.getInt(ELASTIC_CLIENT_CONNECTION_REQUEST_TIMEOUT_PATH) : DEFAULT_CONNECTION_TIMEOUT,
                _configs.hasPath(ELASTIC_CLIENT_USERNAME_PATH) ? _configs.getString(ELASTIC_CLIENT_USERNAME_PATH) : null,
                _configs.hasPath(ELASTIC_CLIENT_PASSWORD_PATH) ? _configs.getString(ELASTIC_CLIENT_PASSWORD_PATH) : null,
                context
        ));
        bind(AnalyticsService.class).toInstance(backend);
    }

    private SSLContext createSSLContext() {
        if (!_configs.hasPath(ELASTIC_CLIENT_USE_SSL_PATH) || !_configs.getBoolean(ELASTIC_CLIENT_USE_SSL_PATH)) {
            // SSL Disabled
            return null;
        }

        final String sslProtocol = _configs.getString(ELASTIC_CLIENT_SSL_PROTOCOL_PATH);
        final String sslSecureRandomImplementation = _configs.getString(ELASTIC_CLIENT_SSL_SECURE_RANDOM_IMPL_PATH);
        final String sslTrustStoreFile = _configs.getString(ELASTIC_CLIENT_SSL_TRUST_STORE_FILE_PATH);
        final String sslTrustStoreType = _configs.getString(ELASTIC_CLIENT_SSL_TRUST_STORE_TYPE_PATH);
        final String sslTrustStorePassword = _configs.getString(ELASTIC_CLIENT_SSL_TRUST_STORE_PASSWORD_PATH);
        final String sslKeyStoreFile = _configs.getString(ELASTIC_CLIENT_SSL_KEY_STORE_FILE_PATH);
        final String sslKeyStoreType = _configs.getString(ELASTIC_CLIENT_SSL_KEY_STORE_TYPE_PATH);
        final String sslKeyStorePassword = _configs.getString(ELASTIC_CLIENT_SSL_KEY_STORE_PASSWORD_PATH);

        final SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        if (sslProtocol != null) {
            sslContextBuilder.useProtocol(sslProtocol);
        }

        if (sslTrustStoreFile != null && sslTrustStoreType != null && sslTrustStorePassword != null) {
            loadKeyStore(sslContextBuilder, sslTrustStoreFile, sslTrustStoreType, sslTrustStorePassword);
        }

        if (sslKeyStoreFile != null && sslKeyStoreType != null && sslKeyStorePassword != null) {
            loadKeyStore(sslContextBuilder, sslKeyStoreFile, sslKeyStoreType, sslKeyStorePassword);
        }

        final SSLContext sslContext;
        try {
            if (sslSecureRandomImplementation != null) {
                sslContextBuilder.setSecureRandom(SecureRandom.getInstance(sslSecureRandomImplementation));
            }
            sslContext = sslContextBuilder.build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to build SSL Context", e);
        }
        return sslContext;
    }

    private void loadKeyStore(@Nonnull SSLContextBuilder sslContextBuilder,
                              @Nonnull String path,
                              @Nonnull String type,
                              @Nonnull String password) {
        try (InputStream identityFile = new FileInputStream(path)) {
            final KeyStore keystore = KeyStore.getInstance(type);
            keystore.load(identityFile, password.toCharArray());
            sslContextBuilder.loadTrustMaterial(keystore, null);
        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new RuntimeException("Failed to load key store: " + path, e);
        }
    }
}