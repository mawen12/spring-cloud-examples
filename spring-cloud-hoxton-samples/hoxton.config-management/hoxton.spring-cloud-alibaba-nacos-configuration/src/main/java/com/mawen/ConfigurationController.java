package com.mawen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/18
 */
@RestController
@RefreshScope
public class ConfigurationController {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${book.author:unknown}")
    private String bookAuthor;

    @Autowired
    private BookProperties bookProperties;

    @GetMapping("/config")
    public String config() {
        StringBuilder sb = new StringBuilder();
        sb.append("env.get('book.category')=" + applicationContext.getEnvironment().getProperty("book.category", "unknown")); // nacos config - environment - propertySource
        sb.append("\nenv.get('book.author')=" + applicationContext.getEnvironment().getProperty("book.author", "unknown")); // bootstrap.properties - environment - propertySource
        sb.append("\nbookAuthor=" + bookAuthor); // bootstrap.properties - environment - class field
        sb.append("\nbookProperties=" + bookProperties);
        return sb.toString();
    }
}
