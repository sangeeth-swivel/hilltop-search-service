package com.hilltop.search.service.domain.response;

import com.hilltop.search.domain.response.HotelListResponseDto;
import com.hilltop.search.domain.response.HotelResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class HotelListResponseDtoTest {
    @Test
    void testConstructorAndGetters() {
        HotelResponseDto hotel1 = new HotelResponseDto(
                "1234",
                "Example Hotel 1",
                "This is an example hotel loaction.",
                Arrays.asList("https://example.com/hotel2/image1.png", "https://example.com/hotel2/image2.png"),
                "Example City 1",
                "123-456-7890",
                "hotel1@example.com"
                );
        HotelResponseDto hotel2 = new HotelResponseDto(
                "5678",
                "Example Hotel 2",
                "This is an example hotel 2.",
                Arrays.asList("https://example.com/hotel2/image1.png", "https://example.com/hotel2/image2.png"),
                "Example City 2",
                "123-456-7890",
                "hotel2@example.com");

        List<HotelResponseDto> hotelList = Arrays.asList(hotel1, hotel2);

        HotelListResponseDto hotelListResponseDto = new HotelListResponseDto(hotelList);

        Assertions.assertThat(hotelListResponseDto.getHotelList()).isEqualTo(hotelList);
    }

    @Test
    void testToLogJson() {
        HotelResponseDto hotel1 = new HotelResponseDto("1234", "Example Hotel 1", "This is an example hotel 1.", Arrays.asList("https://example.com/hotel1/image1.png", "https://example.com/hotel1/image2.png"),"Example City 1", "123-456-7890", "hotel1@example.com");
        HotelResponseDto hotel2 = new HotelResponseDto("5678", "Example Hotel 2", "This is an example hotel 2.", Arrays.asList("https://example.com/hotel2/image1.png", "https://example.com/hotel2/image2.png"),"Example City 2", "123-456-7890", "hotel2@example.com");

        List<HotelResponseDto> hotelList = Arrays.asList(hotel1, hotel2);

        HotelListResponseDto hotelListResponseDto = new HotelListResponseDto(hotelList);

        String expectedJson = "{\"hotelList\":[{\"id\":\"1234\",\"hotelName\":\"Example Hotel 1\",\"hotelLocation\":\"This is an example hotel 1.\",\"imageUrl\":[\"https://example.com/hotel1/image1.png\",\"https://example.com/hotel1/image2.png\"],\"city\":\"Example City 1\",\"contact\":\"123-456-7890\",\"email\":\"hotel1@example.com\"},{\"id\":\"5678\",\"hotelName\":\"Example Hotel 2\",\"hotelLocation\":\"This is an example hotel 2.\",\"imageUrl\":[\"https://example.com/hotel2/image1.png\",\"https://example.com/hotel2/image2.png\"],\"city\":\"Example City 2\",\"contact\":\"123-456-7890\",\"email\":\"hotel2@example.com\"}]}";

        Assertions.assertThat(hotelListResponseDto.toLogJson()).isEqualTo(expectedJson);
    }

}