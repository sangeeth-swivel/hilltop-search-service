package com.hilltop.search.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Room Search ResponseDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSearchResponseDto implements ResponseDto{
    private String hotelId;
    private List<RoomResponseDto> rooms;
}
