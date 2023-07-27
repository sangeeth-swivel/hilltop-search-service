package com.hilltop.search.controller;


import com.hilltop.search.configuration.Translator;
import com.hilltop.search.domain.response.ResponseWrapper;

import com.hilltop.search.domain.response.RoomListResponseDto;
import com.hilltop.search.domain.response.RoomSearchResponseDto;
import com.hilltop.search.enums.SuccessMessage;
import com.hilltop.search.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/search")
public class SearchController extends BaseController {

    private static final String MISSING_FIELDS = "Required fields missing. data: {}";

    private final SearchService searchService;

    public SearchController(Translator translator, SearchService searchService) {
        super(translator);
        this.searchService = searchService;
    }
    @GetMapping("")
    public ResponseEntity<ResponseWrapper> getSearchRoom(@RequestParam int days,
                                                         @RequestParam int paxCount,
                                                         @RequestParam String city) {

        try{
            List<RoomSearchResponseDto> roomSearch = searchService.getRoomSearch(city, days, paxCount);
            RoomListResponseDto roomListResponseDto = new RoomListResponseDto(roomSearch);
            return getSuccessResponse( SuccessMessage.SEARCH_ROOM,roomListResponseDto, HttpStatus.OK);
        }catch (Exception e){
            log.error("", e);
        }
        return null;
    }
}
