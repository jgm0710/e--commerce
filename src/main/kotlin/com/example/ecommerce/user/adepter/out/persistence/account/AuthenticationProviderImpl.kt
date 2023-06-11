package com.example.ecommerce.user.adepter.out.persistence.account

import com.example.ecommerce.user.domain.account.Account
import com.example.ecommerce.user.domain.account.AuthInfo
import com.example.ecommerce.user.domain.account.AuthenticationProvider
import com.example.ecommerce.user.domain.account.exception.PasswordMismatchException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class AuthenticationProviderImpl(
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    override fun encodePassword(password: String): String {
        return passwordEncoder.encode(password)
    }

    override fun validatePassword(account: Account, password: String) {
        if (account.mustHavePassword) {
            val rawPassword: String = checkNotNull(account.password) { "[account.password] 는 null 일 수 없습니다." }
            if (!passwordEncoder.matches(password, rawPassword)) {
                throw PasswordMismatchException()
            }
        } else {
            return
        }
    }

    // TODO: token 정보 처리 필요. 임시로 uuid 토큰 사용. security 설정 사용 x
    override fun createAuthInfo(account: Account): AuthInfo {
        return AuthInfo(memberId = account.memberId.value.toString(),
            accessToken = UUID.randomUUID().toString(),
            accessTokenExpiredAt = Instant.now().plusSeconds(60 * 30),
            refreshToken = UUID.randomUUID().toString(),
            refreshTokenExpiredAt = Instant.now().plusSeconds(60 * 60 * 24)
        )
    }
}
