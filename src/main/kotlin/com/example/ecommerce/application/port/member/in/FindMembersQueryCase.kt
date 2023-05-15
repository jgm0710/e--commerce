package com.example.ecommerce.application.port.member.`in`

import com.example.ecommerce.domain.member.model.MemberSimple
import com.example.ecommerce.global.pagination.PageQuery

interface FindMembersQueryCase {

    fun findAll(pageQuery: PageQuery): List<MemberSimple>
}

operator fun FindMembersQueryCase.invoke(pageQuery: PageQuery) = findAll(pageQuery)
