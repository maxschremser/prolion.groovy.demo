package com.prolion.demo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

import java.time.Duration

@Configuration
@ConfigurationProperties(prefix = "prolion.service")
class FileWalkerConfigurationProperties {
    String dir
    Duration interval
    int maxDepth

}
