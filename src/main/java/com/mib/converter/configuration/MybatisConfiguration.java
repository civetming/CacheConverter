package com.mib.converter.configuration;


import com.mib.converter.wrapper.CustomWrapperFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * @ClassName MybatisConfiguration
 * @Description mybatis配置
 **/
@Configuration
@MapperScan(value = "com.mib.converter.mapper", annotationClass = Repository.class)
public class MybatisConfiguration {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            configuration.setMapUnderscoreToCamelCase(true);
            configuration.setJdbcTypeForNull(JdbcType.NULL);
            configuration.setObjectWrapperFactory(new CustomWrapperFactory());
        };
    }

}
