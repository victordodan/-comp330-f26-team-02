
package edu.loyola.campusconnect.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Validates student support-request fields for CampusConnect.
 *
 * Implements the input-validation portion of API-001.
 * Authorization and persistence are enforced by the workflow service.
 */
public final class RequestValidator {

    private static final Set<String> ALLOWED_CATEGORIES = Set.of(
            "Academic",
            "IT",
            "Campus Services",
            "Other"
    );

    private RequestValidator() {
        // Utility class.
    }

    public static ValidationResult validate(
            String title,
            String category,
            String description) {

        List<String> errors = new ArrayList<>();

        String cleanTitle = title == null ? "" : title.strip();
        String cleanCategory = category == null ? "" : category;
        String cleanDescription =
                description == null ? "" : description.strip();

        if (cleanTitle.length() < 5 || cleanTitle.length() > 100) {
            errors.add("Title must contain 5 to 100 characters.");
        }

        if (!ALLOWED_CATEGORIES.contains(cleanCategory)) {
            errors.add("Category must be Academic, IT, Campus Services, or Other.");
        }

        if (cleanDescription.length() < 10
                || cleanDescription.length() > 2000) {
            errors.add("Description must contain 10 to 2000 characters.");
        }

        return new ValidationResult(
                errors.isEmpty(),
                cleanTitle,
                cleanCategory,
                cleanDescription,
                List.copyOf(errors)
        );
    }

    public record ValidationResult(
            boolean valid,
            String title,
            String category,
            String description,
            List<String> errors) {
    }
}
