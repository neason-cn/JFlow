package com.jflow.infra.spi.script.type;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

/**
 * @author neason
 * @since 0.0.1
 */
public class JsonScript extends Script<JSON> {

    @Override
    public TypeReference<JSON> getResultType() {
        return new TypeReference<JSON>() {
        };
    }

}
