package com.hilltop.search.service.domain.response;

import com.hilltop.search.domain.response.HotelResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class HotelResponseDtoTest {
    @Test
    void testConstructorAndGetters() {
        String id = "1234";
        String hotelName = "Example Hotel";
        String hotelLocation = "Example Hotel Location";
        List<String> imageUrl = Arrays.asList("https://example.com/image1.png", "https://example.com/image2.png");
        String city = "Example City";
        String contact = "123-456-7890";
        String email = "hotel@example.com";

        HotelResponseDto hotelResponseDto = new HotelResponseDto(id, hotelName,hotelLocation, imageUrl, city, contact, email);

        Assertions.assertThat(hotelResponseDto.getId()).isEqualTo(id);
        Assertions.assertThat(hotelResponseDto.getHotelName()).isEqualTo(hotelName);
        Assertions.assertThat(hotelResponseDto.getHotelLocation()).isEqualTo(hotelLocation);
        Assertions.assertThat(hotelResponseDto.getCity()).isEqualTo(city);
        Assertions.assertThat(hotelResponseDto.getContact()).isEqualTo(contact);
        Assertions.assertThat(hotelResponseDto.getEmail()).isEqualTo(email);
        Assertions.assertThat(hotelResponseDto.getImageUrl()).isEqualTo(imageUrl);
    }
}