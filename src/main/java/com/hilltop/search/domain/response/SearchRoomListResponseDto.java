package com.hilltop.search.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRoomListResponseDto implements ResponseDto {
    private List<RoomSearchResponseDto> list;
}