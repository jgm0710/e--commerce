package com.example.ecommerce.adepter.out.persistence.member

import com.example.ecommerce.adepter.out.persistence.member.MemberEntity.Companion.toEntity
import com.example.ecommerce.application.port.member.out.DeleteMemberPort
import com.example.ecommerce.application.port.member.out.FindMemberPort
import com.example.ecommerce.application.port.member.out.SaveMemberPort
import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.global.pagination.PageQuery
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MemberPersistenceAdepter(
    val memberJpaRepository: MemberJpaRepository,
) : SaveMemberPort, FindMemberPort, DeleteMemberPort {

    override fun save(member: Member): Member {
        return memberJpaRepository.save(member.toEntity()).toDomain()
    }

    override fun findById(memberId: MemberId): Member? {
        return memberJpaRepository.findByIdOrNull(memberId.value)?.toDomain()
    }

    override fun findAll(pageQuery: PageQuery): List<Member> {
        return memberJpaRepository.findAll(PageRequest.of((pageQuery.page - 1).toInt(), pageQuery.limit.toInt()))
            .map { it.toDomain() }.toList()
    }

    override fun existsByNickname(nickname: String): Boolean {
        return memberJpaRepository.existsByNickname(nickname)
    }

    override fun deleteById(memberId: MemberId) {
        memberJpaRepository.deleteById(memberId.value)
    }
}
