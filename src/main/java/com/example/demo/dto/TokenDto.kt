package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenDto(

        @field:JsonProperty("token")
        val token: String? = null)