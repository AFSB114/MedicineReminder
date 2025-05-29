package com.sena.medicinereminder.projection;

import java.time.LocalDateTime;

public interface PatientProjection {
    Long getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    LocalDateTime getRegistrationDate();
}
