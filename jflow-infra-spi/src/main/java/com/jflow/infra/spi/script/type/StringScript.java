package com.jflow.infra.spi.script.type;

import com.alibaba.fastjson2.TypeReference;

/**
 * @author neason
 * @since 0.0.1
 */
public class StringScript extends Script<String> {

    public StringScript(String content) {
        super(content);
    }

    @Override
    public TypeReference<String> getResultType() {
        return new TypeReference<String>() {
        };
    }

}
