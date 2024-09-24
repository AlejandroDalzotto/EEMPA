package com.project.varano.payloads

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
data class ApiErrorResponse(
    private val message: String,
    private val reason: Any,
)
