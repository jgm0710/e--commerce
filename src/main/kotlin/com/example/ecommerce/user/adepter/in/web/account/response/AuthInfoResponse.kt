package com.example.ecommerce.user.adepter.`in`.web.account.response

import com.example.ecommerce.user.domain.account.AuthInfo
import java.time.Instant

data class AuthInfoResponse(
    val memberId : String,
    val accessToken : String,
    val accessTokenExpiredAt: Instant,
    val refreshToken : String,
    val refreshTokenExpiredAt: Instant
) {
    companion object {
        fun from(authInfo: AuthInfo)  : AuthInfoResponse {
            return AuthInfoResponse(
                memberId = authInfo.memberId,
                accessToken = authInfo.accessToken,
                accessTokenExpiredAt = authInfo.accessTokenExpiredAt,
                refreshToken = authInfo.refreshToken,
                refreshTokenExpiredAt = authInfo.refreshTokenExpiredAt
            )
        }
    }
}
