package com.hilltop.search.domain.response;

import com.hilltop.search.enums.SuccessMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HotelListResponseWrapper implements ResponseDto {
    private SuccessMessage status;
    private String message;
    private HotelListResponseDto data;
    private String displayMessage;
}
