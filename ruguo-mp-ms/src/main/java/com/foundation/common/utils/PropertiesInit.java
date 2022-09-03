package com.foundation.common.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource(value = "classpath:foundation.properties", ignoreResourceNotFound = false) })
public class PropertiesInit {

}
