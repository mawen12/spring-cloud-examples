package com.mawen;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/18
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "book")
public class BookProperties {

    private String category;

    private String author;


}
