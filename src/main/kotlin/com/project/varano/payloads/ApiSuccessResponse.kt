package com.project.varano.payloads

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
data class ApiSuccessResponse(
    private val data: Any,
    private val message: String,
)
