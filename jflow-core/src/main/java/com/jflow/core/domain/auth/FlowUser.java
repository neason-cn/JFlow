package com.jflow.core.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
public class FlowUser {

    /**
     * The unique id of system user.
     */
    private String userId;

}
