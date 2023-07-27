package com.hilltop.search.enums;

import lombok.Getter;

/**
 * Success messages.
 */
@Getter
public enum SuccessMessage {

    CREATE_BOOKING("Successfully created the booking."),
    SEARCH_ROOM("Successfully returned the search room list.");
    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }
}
