package hicc.toy_project.controller.dto;

import hicc.toy_project.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String memberId;
    private String content;
    private LocalDateTime writtenDate;

    public CommentResponse(Comment comment){
        this.commentId = comment.getCommentId();
        this.memberId = comment.getMember().getId();
        this.content = comment.getContent();
        this.writtenDate = comment.getWrittenDate();

    }
}
