package com.hilltop.search.domain.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Room Search List ResponseDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSearchListResponseDto implements ResponseDto {
    private List<RoomSearchResponseDto> list;
}
