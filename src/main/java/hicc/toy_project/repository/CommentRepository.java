package hicc.toy_project.repository;

import hicc.toy_project.domain.comment.Comment;
import hicc.toy_project.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
     List<Comment> findAllByPostId(Long id);
     List<Comment> findAllByMember(Member id);
}
