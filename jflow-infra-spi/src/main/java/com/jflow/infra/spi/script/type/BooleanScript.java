package com.jflow.infra.spi.script.type;

import com.alibaba.fastjson2.TypeReference;

/**
 * @author neason
 * @since 0.0.1
 */
public class BooleanScript extends Script<Boolean> {

    public BooleanScript(String content) {
        super(content);
    }

    @Override
    public TypeReference<Boolean> getResultType() {
        return new TypeReference<Boolean>() {
        };
    }

}
