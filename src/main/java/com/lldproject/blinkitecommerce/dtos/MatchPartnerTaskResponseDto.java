package com.lldproject.blinkitecommerce.dtos;

import com.lldproject.blinkitecommerce.models.PartnerTaskMapping;
import lombok.Data;

import java.util.List;

@Data
public class MatchPartnerTaskResponseDto {
    private List<PartnerTaskMapping> partnerTaskMappings;
    private ResponseStatus responseStatus;
}
