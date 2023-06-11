package com.example.ecommerce.user.application.port.`in`.member

import com.example.ecommerce.user.domain.member.model.MemberSimple
import com.example.ecommerce.global.pagination.PageQuery

interface FindMembersQueryCase {

    fun findAll(pageQuery: PageQuery): List<MemberSimple>
}

operator fun FindMembersQueryCase.invoke(pageQuery: PageQuery) = findAll(pageQuery)
