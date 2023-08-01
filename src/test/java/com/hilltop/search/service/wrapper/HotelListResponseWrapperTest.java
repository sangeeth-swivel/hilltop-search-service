package com.hilltop.search.service.wrapper;

import com.hilltop.search.domain.response.HotelListResponseDto;
import com.hilltop.search.domain.response.HotelListResponseWrapper;
import com.hilltop.search.enums.ResponseStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HotelListResponseWrapperTest {
    @Test
    void Should_testConstructorAndGetters() {

        ResponseStatusType status = ResponseStatusType.SUCCESS;
        String message = "Hotels retrieved successfully";
        HotelListResponseDto data = new HotelListResponseDto();
        String displayMessage = "Hotels retrieved successfully";
        HotelListResponseWrapper responseWrapper = new HotelListResponseWrapper();
        responseWrapper.setStatus(status);
        responseWrapper.setMessage(message);
        responseWrapper.setData(data);
        responseWrapper.setDisplayMessage(displayMessage);
        Assertions.assertEquals(status, responseWrapper.getStatus());
        Assertions.assertEquals(message, responseWrapper.getMessage());
        Assertions.assertEquals(data, responseWrapper.getData());
        Assertions.assertEquals(displayMessage, responseWrapper.getDisplayMessage());
    }

    @Test
    void Should_testToLogJson() {

        ResponseStatusType status = ResponseStatusType.ERROR;
        String message = "Error occurred";
        HotelListResponseDto data = new HotelListResponseDto();
        String displayMessage = "Something went wrong";
        HotelListResponseWrapper responseWrapper = new HotelListResponseWrapper();
        responseWrapper.setStatus(status);
        responseWrapper.setMessage(message);
        responseWrapper.setData(data);
        responseWrapper.setDisplayMessage(displayMessage);
        String logJson = responseWrapper.toLogJson();
        Assertions.assertNotNull(logJson);
        Assertions.assertTrue(logJson.contains("\"status\":\"ERROR\""));
        Assertions.assertTrue(logJson.contains("\"message\":\"Error occurred\""));
        Assertions.assertTrue(logJson.contains("\"displayMessage\":\"Something went wrong\""));
    }
}