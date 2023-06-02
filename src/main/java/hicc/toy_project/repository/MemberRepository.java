package hicc.toy_project.repository;

import hicc.toy_project.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByIdNumber(String id);
    void deleteByIdNumber(String id);
}
