package com.example.ecommerce.user.domain.member.exception

import com.example.ecommerce.global.exception.AbstractConflictException

class DuplicateNicknameException(nickname: String) : AbstractConflictException(MemberErrorCode.DUPLICATE_NICKNAME) {

    override val message: String = "Nickname 이 기존 회원의 닉네임과 중복됩니다. [$nickname]"
}
