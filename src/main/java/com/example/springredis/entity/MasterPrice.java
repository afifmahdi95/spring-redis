package com.example.springredis.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Entity
@Table(name = MasterPrice.TABLE_NAME)
public class MasterPrice implements Serializable {
    public static final String TABLE_NAME = "master_price";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @TableGenerator(name = TABLE_NAME, table = "T_SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = TABLE_NAME, valueColumnName = "SEQ_VAL", allocationSize = 1, initialValue = 10000)
    @Column(name = "id", length = 11)
    private BigInteger id;

    @Column(name = "origin_code")
    private String originCode;

    @Column(name = "destination_code")
    private String destinationCode;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "product")
    private String product;
}
