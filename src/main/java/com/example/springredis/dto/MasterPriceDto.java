package com.example.springredis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class MasterPriceDto implements Serializable {

    private String originCode;
    private String destinationCode;
    private BigDecimal price;
    private String product;
}
