package com.csys.workflowDemande.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:OptionVersion.yml")
@ConfigurationProperties

public class OptionVersion {




}
