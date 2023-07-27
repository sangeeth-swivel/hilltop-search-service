package com.hilltop.search.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Room List ResponseDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomListResponseDto implements ResponseDto {
    private List<RoomSearchResponseDto> list;
}
