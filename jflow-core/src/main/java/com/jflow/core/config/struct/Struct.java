package com.jflow.core.config.struct;

import java.io.Serializable;
import java.util.Map;

import com.jflow.core.enums.StructTypeEnum;
import lombok.Data;

/**
 * 通用的结构体表示
 *
 * @author : neason
 * @date : 2021/11/29
 */
@Data
public class Struct implements Serializable {

    private static final long serialVersionUID = 2021112968134190717L;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 数据类型
     */
    private StructTypeEnum type;

    /**
     * 当 type=StructTypeEnum.OBJECT 时，用于描述这个对象的结构体
     */
    private Map<String, Struct> properties;

    /**
     * 当 type=StructTypeEnum.ARRAY 时，用于描述数组或列表里面元素的结构体
     */
    private Struct arrayItem;

    /**
     * 当 type=StructTypeEnum.MAP 时，用于描述Map的value的结构体，key只能是STRING
     */
    private Struct mapValue;

    /**
     * 是否弃用
     */
    private boolean deprecated;

    /**
     * 是否必填
     */
    private boolean required;

}
