package com.hilltop.search.domain.response;

import com.hilltop.search.enums.SuccessMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomListResponseWrapper implements ResponseDto {
    private SuccessMessage status;
    private String message;
    private SearchRoomListResponseDto data;
    private String displayMessage;
}
