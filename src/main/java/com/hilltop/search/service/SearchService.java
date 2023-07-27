package com.hilltop.search.service;

import com.hilltop.search.domain.response.*;
import com.hilltop.search.exception.HillTopSearchApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchService {

    private final RestTemplate restTemplate;

    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RoomSearchResponseDto> getRoomSearch(String city, int dayCount, int paxCount) {
        List<String> hotelsIdByCity = getHotelsIdByCity(city);
        return getRoomsByHotelIdWithPaxCountAndDays(hotelsIdByCity, dayCount, paxCount);
    }

    public List<String> getHotelsIdByCity(String city) {
        try {
            String trimCity = city.trim();
//            String getHotelIdUrl = "http://3.84.168.202:8083/hotel-service/api/v1/hotel/city/%s";
            String getHotelIdUrl = "http://localhost:5002/hilltop-hotel-service/api/v1/hotel/city/%s";
            String formattedURL = String.format(getHotelIdUrl, trimCity);
            ResponseEntity<HotelListResponseWrapper> result =
                    restTemplate.exchange(formattedURL, HttpMethod.GET, null, HotelListResponseWrapper.class);
            HotelListResponseDto hotelList = Objects.requireNonNull(result.getBody()).getData();
            return hotelList.getHotelList().stream().map(HotelResponseDto::getId).collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new HillTopSearchApplicationException("Getting hotel ids by city was failed.",e);
        }
    }
    public List<RoomSearchResponseDto> getRoomsByHotelIdWithPaxCountAndDays(List<String> hotelIds, int dayCount, int paxCount) {

        try {
            String hotelIdsUrlPrefix = creteHotelIdsUrlPrefix(hotelIds);
//            String getRoomsUrl = "http://3.90.84.171:8084/room-service/api/v1/room/list-hotel-room-by?count=" + paxCount + "&days=" + dayCount + hotelIdsUrlPrefix;
            String getRoomsUrl = "http://localhost:5002/hilltop-hotel-service/api/v1/room/list-hotel-room-by?count=" + paxCount + "&days=" + dayCount + hotelIdsUrlPrefix;
            ResponseEntity<RoomListResponseWrapper> result =
                    restTemplate.exchange(getRoomsUrl, HttpMethod.GET, null, RoomListResponseWrapper.class);
            SearchRoomListResponseDto data = Objects.requireNonNull(result.getBody()).getData();
            return data.getList();
        } catch (RuntimeException e) {
            throw new HillTopSearchApplicationException("Getting Search room was failed.", e);
        }
    }

    public String creteHotelIdsUrlPrefix(List<String> hotelIds) {
        StringBuilder urlBuilder = new StringBuilder();
        for (var id : hotelIds) {
            urlBuilder.append("&hotelIds=").append(id);
        }
        return urlBuilder.toString();
    }

}
