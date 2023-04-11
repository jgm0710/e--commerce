package com.example.ecommerce.domain.account.exception

import com.example.ecommerce.global.exception.AbstractConflictException

class DuplicateLoginIdException(loginId : String):AbstractConflictException("기존 회원과 LoginId 가 중복됩니다. [$loginId]")
