package hicc.toy_project.repository;

import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByMemberIdNumber(String id);
}
