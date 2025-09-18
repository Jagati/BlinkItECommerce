package com.lldproject.blinkitecommerce.dtos;

import com.lldproject.blinkitecommerce.models.Location;
import lombok.Data;

import java.util.List;

@Data
public class BuildBatchedTaskRouteResponseDto {
    private List<Location> routeToBeTaken;

    private ResponseStatus status;
}
