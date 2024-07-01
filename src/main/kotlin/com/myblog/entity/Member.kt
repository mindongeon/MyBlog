package com.myblog.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "member")
class Member(
    id: Long = 0,
    email: String,
    password: String,
    role: Role
) : AuditingEntity(){

    @Column(name= "email", nullable = false)
    var email: String = email
        private set

    @Column(name= "password", nullable = false)
    var password: String = password
        private set

    @Enumerated(EnumType.STRING)
    var role: Role = role
        private set

    companion object {
        fun createFakeMember(memberId: Long): Member = Member(
            id = memberId,
            email = "test@test.com",
            password = "1234",
            Role.JJABARI
        )
    }

    fun toDto(): MemberRes = MemberRes(

    )
}

enum class Role {
    HOST, JJABARI
}


data class MemberRes(
    var id: Long,
    var email: String,
    var password: String,
    var role: Role,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
)

data class MemberSaveReq(
    var email: String,
    var password: String,
) {
    fun toEntity(): Member = Member(
        email = this.email,
        password = this.password,
        role = Role.JJABARI
    )
}
