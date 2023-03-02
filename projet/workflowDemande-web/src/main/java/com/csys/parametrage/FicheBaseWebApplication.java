package com.csys.parametrage;

import java.io.File;
import org.springframework.boot.ApplicationHome;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
public class FicheBaseWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationHome home = new ApplicationHome(FicheBaseWebApplication.class);
        File src = home.getSource();
        if (src != null) {
            src = src.getParentFile();
        }
        File tmp = new File(src, "Tmp");
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        System.setProperty("java.io.tmpdir", tmp.toString());
        SpringApplication.run(FicheBaseWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FicheBaseWebApplication.class);
    }
}
