package hicc.toy_project.controller.dto;

import hicc.toy_project.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponse {
    private Long postId;
    private String memberId;
    private String title;
    private String content;
    private LocalDateTime writtenDate;

    public PostResponse(Post post) {
        this.postId = post.getPostId();
        this.memberId = post.getMember().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writtenDate = post.getWrittenDate();
    }
}
