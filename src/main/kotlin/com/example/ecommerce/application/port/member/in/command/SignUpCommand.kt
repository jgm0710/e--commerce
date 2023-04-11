package com.example.ecommerce.application.port.member.`in`.command

import com.example.ecommerce.domain.account.Account
import com.example.ecommerce.domain.account.AccountType
import com.example.ecommerce.domain.member.Agreement
import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberAddress
import com.example.ecommerce.domain.member.MemberId
import java.time.LocalDate

data class SignUpCommand(
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val agreements: List<Agreement>,
    val memberAddress: MemberAddress,
    val loginId: String,
    val password: String,
) {

    fun createMember(): Member {
        return Member(
            name = name,
            nickname = nickname,
            birth = birth,
            email = email,
            phone = phone,
            tel = tel,
            agreements = agreements,
            memberAddress = memberAddress
        )
    }

    fun createAccount(id: MemberId, encodePassword: (password: String) -> String): Account {
        return Account(
            memberId = id,
            accountType = AccountType.EMAIL,
            loginId = loginId,
            password = encodePassword(password)
        )
    }
}
