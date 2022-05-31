package com.jflow.infra.spi.script;

/**
 * The string script content wrapper.
 *
 * @author neason
 * @since 0.0.1
 */
public interface Script {

    /**
     * @return the type of script, such as QLExpress, Groovy, SpEL etc.
     */
    String getType();

    /**
     * @return the content of script
     */
    String getContent();

}
