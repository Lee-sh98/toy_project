package hicc.toy_project.domain.post;

import hicc.toy_project.domain.member.Member;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long postId;
    private PostType postType;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    private String title;
    private String content;
    private LocalDateTime writtenDate;

}
