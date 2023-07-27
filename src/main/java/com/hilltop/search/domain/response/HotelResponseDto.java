package com.hilltop.search.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Hotel ResponseDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto implements ResponseDto {
    private String id;
    private String hotelName;
    private String hotelLocation;
    private List<String> imageUrl;
    private String city;
    private String contact;
    private String email;
}
