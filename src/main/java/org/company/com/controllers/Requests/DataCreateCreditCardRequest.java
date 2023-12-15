package org.company.com.controllers.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.company.com.domain.model.JobStabilityStatus;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DataCreateCreditCardRequest {

    private final String income;
    private final JobStabilityStatus jobStabilityStatus;
    private final String name;
    private final String lastName;
    private final String email;
    private final UUID userId;

}
