package com.mawen.gateway.zuul.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 用户实体类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/4
 */
@Setter
@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;

}
