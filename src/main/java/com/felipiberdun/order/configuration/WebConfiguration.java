package com.felipiberdun.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Configuration
public class WebConfiguration {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        final DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }

}
