package com.jflow.api.client.request.commands;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@ApiModel
public class DisableSpecScheduleCommand {
    @ApiModelProperty(value = "the unique id of flow spec", required = true)
    private String flowSpecId;
}
