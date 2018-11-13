package com.example.demo.dto

import org.codehaus.jackson.annotate.JsonProperty

data class FormLoginDto(
        @field:JsonProperty("userId")
        val id: String?= null,

        @field:JsonProperty("password")
        val password: String?= null
)