package org.jupiter;

import org.jupiter.config.JupiterConfig;
import org.jupiter.rpc.JClient;
import org.jupiter.rpc.JServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jupiter自动配置类
 *
 * @author yanglinlin newboy2004@126.com
 * @create 2017-12-15 14:36
 */

@Configuration
@EnableConfigurationProperties(JupiterConfig.class)
@ConditionalOnClass(JServer.ServiceRegistry.class)
public class JupiterAutoConfiguration {

    @Autowired
    JupiterConfig config;


    @Bean
    public JServer jServer(){
        return config.getDefaultServer();
    }

    @Bean
    public JClient jClient(){
        return config.getDefaultClient();
    }
}
