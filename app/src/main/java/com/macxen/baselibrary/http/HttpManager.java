package com.macxen.baselibrary.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author lainuo
 * @name BaseLibrary
 * @class name：com.macxen.baselibrary.http
 * @time 2018/11/13 14:14
 * @describe describe
 */
public class HttpManager {
    private static String API_BASE_URL = "";
    private static int READ_TIMEOUT = 250;
    private static int WRITE_TIMEOUT = 250;
    private static int CONNECT_TIMEOUT = 250;
    private static boolean HTTPS;
    private static Interceptor INTERCEPTOR;
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder reBuilder;

    private HttpManager() {
    }

    private void instance() {
        httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        if (INTERCEPTOR != null) {
            httpClient.addInterceptor(INTERCEPTOR);
        }
        if (HTTPS) {
            httpClient.sslSocketFactory(getSSlSocketFactory());
            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        }
        OkHttpClient client = httpClient.build();

        reBuilder = new Retrofit.Builder();
        reBuilder.baseUrl(API_BASE_URL);
        reBuilder.addConverterFactory(JacksonConverterFactory.create());
        reBuilder.client(client);
    }

    public Retrofit getRetrofit() {
        return reBuilder.build();
    }

    private SSLSocketFactory getSSlSocketFactory() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] x509Certificates = new X509Certificate[0];
                            return x509Certificates;
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            return sslSocketFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class Builder {
        private HttpManager manager = new HttpManager();

        public Builder setBaseUrl(String baseUrl) {
            API_BASE_URL = baseUrl;
            return this;
        }

        public Builder setReadTimeOut(int readTime) {
            READ_TIMEOUT = readTime;
            return this;
        }

        public Builder setWriteTimeOut(int writeTimeOut) {
            WRITE_TIMEOUT = writeTimeOut;
            return this;
        }

        public Builder setConnectTimeOut(int connectTimeOut) {
            CONNECT_TIMEOUT = connectTimeOut;
            return this;
        }

        public Builder setHttps(boolean https) {
            HTTPS = https;
            return this;
        }

        public Builder setIntercept(Interceptor intercept) {
            INTERCEPTOR = intercept;
            return this;
        }

        public HttpManager build() {
            manager.instance();
            return manager;
        }
    }
}
