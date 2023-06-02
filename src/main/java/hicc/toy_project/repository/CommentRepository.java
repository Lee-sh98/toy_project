package hicc.toy_project.repository;

import hicc.toy_project.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    public List<Comment> findAllByPostId(Long id);
    public List<Comment> findAllByMemberIdNumber(String id);
}
