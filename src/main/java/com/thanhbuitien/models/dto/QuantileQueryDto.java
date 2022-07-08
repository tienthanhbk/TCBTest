package com.thanhbuitien.models.dto;

import lombok.Data;

@Data
public class QuantileQueryDto {
    private Long poolId;
    private Double percentile;
}
