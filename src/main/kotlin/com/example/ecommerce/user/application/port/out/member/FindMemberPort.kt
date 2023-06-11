package com.example.ecommerce.user.application.port.out.member

import com.example.ecommerce.user.domain.member.Member
import com.example.ecommerce.user.domain.member.MemberId
import com.example.ecommerce.global.pagination.PageQuery

interface FindMemberPort {

    fun findById(memberId: MemberId): Member?

    fun findAll(pageQuery: PageQuery) : List<Member>

    fun existsByNickname(nickname: String): Boolean
}
