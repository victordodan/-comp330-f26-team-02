
package edu.loyola.campusconnect.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RequestValidatorTest {

    @Test
    void acceptsValidRequestAndTrimsWhitespace() {
        var result = RequestValidator.validate(
                "  WiFi issue  ",
                "IT",
                "  Campus WiFi is not connecting.  "
        );

        assertTrue(result.valid());
        assertEquals("WiFi issue", result.title());
        assertEquals("Campus WiFi is not connecting.", result.description());
        assertTrue(result.errors().isEmpty());
    }

    @Test
    void acceptsMinimumAndMaximumBoundaries() {
        assertTrue(RequestValidator.validate(
                "a".repeat(5), "Academic", "b".repeat(10)
        ).valid());

        assertTrue(RequestValidator.validate(
                "a".repeat(100), "Campus Services", "b".repeat(2000)
        ).valid());
    }

    @Test
    void rejectsOutOfRangeBoundaries() {
        assertFalse(RequestValidator.validate(
                "a".repeat(4), "IT", "b".repeat(10)
        ).valid());

        assertFalse(RequestValidator.validate(
                "a".repeat(101), "IT", "b".repeat(10)
        ).valid());

        assertFalse(RequestValidator.validate(
                "a".repeat(5), "IT", "b".repeat(9)
        ).valid());

        assertFalse(RequestValidator.validate(
                "a".repeat(5), "IT", "b".repeat(2001)
        ).valid());
    }

    @Test
    void rejectsMissingAndWhitespaceOnlyFields() {
        assertFalse(RequestValidator.validate(
                null, "IT", "Valid description"
        ).valid());

        assertFalse(RequestValidator.validate(
                "     ", "IT", "Valid description"
        ).valid());

        assertFalse(RequestValidator.validate(
                "Valid title", "IT", null
        ).valid());

        assertFalse(RequestValidator.validate(
                "Valid title", "IT", "    "
        ).valid());
    }

    @Test
    void rejectsUnsupportedCategories() {
        assertFalse(RequestValidator.validate(
                "Valid title", "Sports", "Valid description"
        ).valid());

        assertFalse(RequestValidator.validate(
                "Valid title", null, "Valid description"
        ).valid());
    }

    @Test
    void acceptsEverySupportedCategory() {
        for (String category : new String[]{
                "Academic", "IT", "Campus Services", "Other"
        }) {
            assertTrue(RequestValidator.validate(
                    "Valid title", category, "Valid description"
            ).valid());
        }
    }
}
