package com.lldproject.blinkitecommerce.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MatchPartnerTaskRequestDto {
    private List<Long> partnerIds;
    private List<Long> taskIds;
}
