package com.csys.access;

import com.csys.access.config.ApplicationProperties;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
@EnableCircuitBreaker
@RefreshScope

public class AccessApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AccessApplication.class);
    @Value("${path_tmp_dir}")
    String pathTmpDir;

    public String getPathTmpDir() {
        return pathTmpDir;
    }

    public void setPathTmpDir(String pathTmpDir) {
        this.pathTmpDir = pathTmpDir;
    }
    public static void main(String[] args) throws UnknownHostException, IOException {
        SpringApplication app = new SpringApplication(AccessApplication.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t"
                + "Application's name '{}' is running! Access URLs:\n\t"
                + "Local: \t\t{}://localhost:{}\n\t"
                + "External: \t{}://{}:{}\n\t"
                + "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
    
    
     
    @Override
    public void run(String... args) throws Exception {
        if (!getPathTmpDir().isEmpty()) {
            File tmp = new File(getPathTmpDir(), "Tmp");
            if (!tmp.exists()) {
                tmp.mkdirs();
            } 
            System.setProperty("java.io.tmpdir", tmp.getAbsolutePath());
        }
    }

}
