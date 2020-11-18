package com.prolion.demo.config


import com.prolion.demo.api.FileResource
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration

@Configuration
class JerseyConfig extends ResourceConfig {
    JerseyConfig() {
        register(FileResource)
    }
}
