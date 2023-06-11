package com.example.ecommerce.user.adepter.out.persistence.mebmer

import com.example.ecommerce.user.adepter.out.persistence.mebmer.MemberEntity.Companion.toEntity
import com.example.ecommerce.user.application.port.out.member.DeleteMemberPort
import com.example.ecommerce.user.application.port.out.member.FindMemberPort
import com.example.ecommerce.user.application.port.out.member.SaveMemberPort
import com.example.ecommerce.user.domain.member.Member
import com.example.ecommerce.user.domain.member.MemberId
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
