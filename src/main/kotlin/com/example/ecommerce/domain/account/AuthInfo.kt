package com.example.ecommerce.domain.account

import java.time.Instant

class AuthInfo(
    val memberId : String,
    val accessToken : String,
    val accessTokenExpiredAt: Instant,
    val refreshToken : String,
    val refreshTokenExpiredAt: Instant
)
