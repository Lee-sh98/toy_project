package hicc.toy_project.repository;

import hicc.toy_project.domain.member.DeletedMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedMemberRepository extends JpaRepository<DeletedMember, String> {
}
