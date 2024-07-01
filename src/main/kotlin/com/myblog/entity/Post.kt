package com.myblog.entity

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "post")
class Post(
    title: String,
    content: String,
    member: Member
) : AuditingEntity(){

    @Column(name = "title")
    var title: String = title
        private set

    @Column(name = "content")
    var content: String = content
        private set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
        private set
}

data class PostRes(
    var id: Long,
    var title: String,
    var content: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var member: MemberRes
)

data class PostSaveReq (
    @field:NotNull("title can not be null")
    var title: String,
    var content: String,
    var memberId: Long
) {
    fun toEntity(): Post = Post(
        title = this.title,
        content = this.content,
        member = Member.createFakeMember(this.memberId)
    )
}
