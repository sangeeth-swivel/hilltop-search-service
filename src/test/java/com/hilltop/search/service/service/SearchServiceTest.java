package com.hilltop.search.service.service;

import com.hilltop.search.domain.response.HotelListResponseWrapper;
import com.hilltop.search.domain.response.RoomListResponseWrapper;
import com.hilltop.search.domain.response.RoomSearchResponseDto;
import com.hilltop.search.domain.response.SearchRoomListResponseDto;
import com.hilltop.search.exception.HillTopSearchApplicationException;
import com.hilltop.search.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class SearchServiceTest {

    private static final List<String> HOTEL_IDS = Collections.singletonList("123");
    private static final int DAY_COUNT = 3;
    private static final int PAX_COUNT = 2;

    private SearchService searchService;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        initMocks(this);
        searchService = new SearchService(restTemplate);
    }

    @Test
    void testCreateHotelIdsUrlPrefix() {
        List<String> hotelIds = Arrays.asList("123", "456", "789");
        String expectedUrlPrefix = "&hotelIds=123&hotelIds=456&hotelIds=789";
        String actualUrlPrefix = searchService.creteHotelIdsUrlPrefix(hotelIds);
        assertEquals(expectedUrlPrefix, actualUrlPrefix);
    }

//    @Test
    void testGetRoomsByHotelIdWithPaxCountAndDays_Success() {

        String hotelIdsUrlPrefix = "&hotelIds=123";
        String getRoomsUrl = "http://localhost:8084/room-service/api/v1/room/list-hotel-room-by?count=2&days=3" + hotelIdsUrlPrefix;
        RoomSearchResponseDto roomSearchResponseDto = new RoomSearchResponseDto();
        SearchRoomListResponseDto searchRoomListResponseDto = new SearchRoomListResponseDto();
        searchRoomListResponseDto.setList(Collections.singletonList(roomSearchResponseDto));
        RoomListResponseWrapper roomListResponseWrapper = new RoomListResponseWrapper();
        roomListResponseWrapper.setData(searchRoomListResponseDto);
        ResponseEntity<RoomListResponseWrapper> responseEntity = ResponseEntity.ok(roomListResponseWrapper);
        when(restTemplate.exchange(getRoomsUrl, HttpMethod.GET, null, RoomListResponseWrapper.class)).thenReturn(responseEntity);
        List<RoomSearchResponseDto> result = searchService.getRoomsByHotelIdWithPaxCountAndDays(HOTEL_IDS, DAY_COUNT, PAX_COUNT);
        assertEquals(1, result.size());
        assertEquals(result.get(0), roomSearchResponseDto);
    }


    @Test
    void testGetRoomsByHotelIdWithPaxCountAndDays_Failure() {

        String hotelIdsUrlPrefix = "&hotelIds=123";
        String getRoomsUrl = "http://localhost:8084/room-service/api/v1/room/list-hotel-room-by?count=2&days=3" + hotelIdsUrlPrefix;
        when(restTemplate.exchange(getRoomsUrl, HttpMethod.GET, null, RoomListResponseWrapper.class))
                .thenThrow(new RuntimeException());
        HillTopSearchApplicationException searchServiceException = assertThrows(HillTopSearchApplicationException.class,
                () -> searchService.getRoomsByHotelIdWithPaxCountAndDays(HOTEL_IDS, DAY_COUNT, PAX_COUNT));
        assertEquals("Getting Search room was failed.", searchServiceException.getMessage());

    }

    @Test
    void Should_Throw_SearchServiceException() {
        String city = "City 1";
        String getHotelIdUrl = "http://localhost:8083/hotel-service/api/v1/hotel/city/" + city;
        when(restTemplate.exchange(getHotelIdUrl, HttpMethod.GET, null, HotelListResponseWrapper.class))
                .thenThrow(new RuntimeException("Error"));
        assertThrows(HillTopSearchApplicationException.class, () -> searchService.getHotelsIdByCity(city));
    }

}