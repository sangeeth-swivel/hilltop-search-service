package com.hilltop.search.service.domain.request;

import com.hilltop.search.domain.request.RequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RequestDtoTest {
    @Test
    void TestIsRequiredFieldsAvailable() {
        RequestDto requestDto = new RequestDto() {
            /**
             * Used to validate required fields.
             *
             * @return true/false
             */
            @Override
            public boolean isRequiredFieldsAvailable() {
                return true;
            }

            @Override
            public String toLogJson() {
                return null;
            }
            // Empty implementation for testing purposes
        };

        Assertions.assertTrue(requestDto.isRequiredFieldsAvailable());
    }

    @Test
    void testIsNonEmpty() {
        RequestDto requestDto = new RequestDto() {

            /**
             * Used to validate required fields.
             *
             * @return true/false
             */
            @Override
            public boolean isRequiredFieldsAvailable() {
                return false;
            }

            @Override
            public String toLogJson() {
                return null;
            }
            // Empty implementation for testing purposes
        };

        String nonEmptyString = "Non-empty string";
        String emptyString = "";
        String nullString = null;

        Assertions.assertTrue(requestDto.isNonEmpty(nonEmptyString));
        Assertions.assertFalse(requestDto.isNonEmpty(emptyString));
        Assertions.assertFalse(requestDto.isNonEmpty(nullString));
    }
}