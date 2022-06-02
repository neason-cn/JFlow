package com.jflow.core.domain.flow.convertor;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.common.utils.JsonUtils;
import com.jflow.core.domain.enums.type.ActionTypeEnum;
import com.jflow.core.domain.flow.reference.spec.action.AbstractActionSpec;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class ActionSpecConvertor {

    public AbstractActionSpec convert(JSONObject json) {
        String type = json.getString(Type.KEY_IN_JSON);
        return json.to(ActionTypeEnum.of(type).getClazz());
    }

    public JSONObject convert(AbstractActionSpec spec) {
        return JsonUtils.toJson(spec);
    }

}
