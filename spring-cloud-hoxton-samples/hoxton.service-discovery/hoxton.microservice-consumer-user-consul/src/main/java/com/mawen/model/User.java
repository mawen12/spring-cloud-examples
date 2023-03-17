package com.mawen.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/8
 */
@Data
public class User implements Serializable {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;

}
