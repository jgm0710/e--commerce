package com.example.ecommerce.application.port.member.out

import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.global.pagination.PageQuery

interface FindMemberPort {

    fun findById(memberId: MemberId): Member?

    fun findAll(pageQuery: PageQuery) : List<Member>

    fun existsByNickname(nickname: String): Boolean
}
