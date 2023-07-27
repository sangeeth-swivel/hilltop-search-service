package com.hilltop.search.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Room ResponseDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDto implements ResponseDto {
    private String id;
    private String roomNo;
    private String roomType;
    private int paxCount;
    private List<String> imageUrls;
    private BigDecimal cost;
}
