package com.jflow.app.endpoint.http.config;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author neason
 * @since 0.0.1
 */
@Configurable
public class HttpJsonSerializerConfig {

    @Bean
    public FastJsonHttpMessageConverter fastjson2Convertor() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(fastMediaTypes);
        FastJsonConfig config = new FastJsonConfig();
        config.setCharset(StandardCharsets.UTF_8);
        config.setWriterFeatures(
                JSONWriter.Feature.WriteEnumsUsingName,
                JSONWriter.Feature.IgnoreNoneSerializable,
                JSONWriter.Feature.ReferenceDetection);
        converter.setFastJsonConfig(config);
        return converter;
    }

}