package com.yeongenn.kopringstudy.kopring.domain.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CommonResponseDto<T>(
    val isSuccess: Boolean,
    val data: T? = null
)
