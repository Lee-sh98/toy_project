package hicc.toy_project.domain.comment;

import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private long commentId;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    private String content;
    private LocalDateTime writtenDate;
}
