package com.mawen.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 用户类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/23
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal balance;
}
