package com.hilltop.search.service.controller;

import com.hilltop.search.configuration.Translator;
import com.hilltop.search.controller.BaseController;
import com.hilltop.search.controller.GlobalControllerExceptionHandler;
import com.hilltop.search.controller.SearchController;
import com.hilltop.search.domain.response.ResponseWrapper;
import com.hilltop.search.exception.DataNotFoundException;
import com.hilltop.search.exception.HillTopSearchApplicationException;
import com.hilltop.search.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SearchControllerTest {

    private static final String SEARCH_URL = "/api/v1/search?city=kandy&count=1&days=1";

    @Mock
    private SearchService searchService;
    @Mock
    private Translator translator;
    private MockMvc mockMvc;
    @Mock
    private BaseController baseController;
    @InjectMocks
    private GlobalControllerExceptionHandler globalControllerExceptionHandler;

    @BeforeEach
    void setUp() {
        initMocks(this);
        SearchController searchController = new SearchController(translator, searchService);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }


    @Test
    void Should_ReturnOk_When_Calling_RoomSearch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SEARCH_URL)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void Should_ReturnInternalServerError_When_BookingIsFailedDueToInternalErrors(){
        HillTopSearchApplicationException exception = new HillTopSearchApplicationException("Failed.");
        ResponseEntity<ResponseWrapper> expectedResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(baseController.getInternalServerError()).thenReturn(expectedResponse);

        ResponseEntity<ResponseWrapper> actualResponse = globalControllerExceptionHandler.hillTopSearchApplicationException(exception);

        verify(baseController, times(1)).getInternalServerError();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }

    /**
     * Unit test for DataNotFoundException
     */
    @Test
    void Should_ReturnDataNotFoundException() {
        String errorMessage = "Data not found!";
        DataNotFoundException exception = assertThrows(DataNotFoundException.class, () -> {
            throw new DataNotFoundException(errorMessage);
        });
        assertEquals(errorMessage, exception.getMessage());
    }
}