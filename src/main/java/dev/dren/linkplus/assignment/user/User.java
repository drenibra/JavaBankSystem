package dev.dren.linkplus.assignment.user;

import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.Date;

public record User(
    Integer id,
    @NotEmpty
    String firstName,
    @NotEmpty
    String lastName,
    Date dateOfBirth
) {
    public User {
        if (!dateOfBirth.toInstant().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Birth date should be before today's date");
        }
    }
}
