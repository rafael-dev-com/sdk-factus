package nellehsoft.sdk_factus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import okhttp3.OkHttpClient;
import nellehsoft.sdk_factus.client.FactusApi;
import nellehsoft.sdk_factus.client.AuthClient;
import nellehsoft.sdk_factus.interceptor.AuthInterceptor;
import nellehsoft.sdk_factus.model.Auth;
import nellehsoft.sdk_factus.provider.auth.TokenProvider;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Api {

    protected final FactusApi apiClient;
    private final TokenProvider tokenProvider;

    public Api(Auth auth, String urlBase){

        var okHttp = this.getOkHttpClient();

        AuthClient authClient = createFeignClient(
                AuthClient.class,
                urlBase,
                builder -> builder
                        .encoder(new FormEncoder())
                        .decoder(new JacksonDecoder()),
                okHttp
        );

        this.tokenProvider = new TokenProvider(authClient, auth);

        this.apiClient = createFeignClient(
                FactusApi.class,
                urlBase,
                builder -> builder
                        .decoder(new JacksonDecoder())
                        .encoder(new JacksonEncoder(getMapper()))
                        .requestInterceptor(new AuthInterceptor(tokenProvider)),
                okHttp
        );
    }

    private ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper;
    }

    private OkHttpClient getOkHttpClient(){
         return new OkHttpClient.Builder()
                        .connectTimeout(3, TimeUnit.SECONDS)
                        .readTimeout(5, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(false)
                        .build();
    }

    private <T> T createFeignClient(Class<T> clientClass, String url, Consumer<Feign.Builder> customizer, OkHttpClient okHttp) {

        Feign.Builder builder = Feign.builder()
                .client(new feign.okhttp.OkHttpClient(okHttp))
                .logger(new Slf4jLogger(clientClass))
                .logLevel(Logger.Level.FULL)
                .retryer(Retryer.NEVER_RETRY);

        if (customizer != null) {
            customizer.accept(builder);
        }

        return builder.target(clientClass, url);
    }

}
