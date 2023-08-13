package hicc.toy_project.adminpage;


import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import hicc.toy_project.exception.CustomException;
import hicc.toy_project.exception.ErrorCode;
import hicc.toy_project.repository.MemberRepository;
import hicc.toy_project.service.AdminPageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Transactional
@SpringBootTest
public class AdminPageTest {
    @Autowired
    PlatformTransactionManager transactionManager;

    TransactionStatus status;

    @Autowired
    AdminPageService adminPageService;

    @Autowired
    MemberRepository memberRepository;

    String presidentId = "C011003";
    String testTargetId = "C011004";

    Member getMember(String id) {
        return memberRepository.findByIdNumber(id)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.MEMBER_NOT_FOUND)
                );
    }

    @BeforeEach
    void beforeEach() {
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        Member president = new Member(presidentId, "testPresident", "010");
        Member testTarget = new Member(testTargetId, "testTarget", "011");

        president.setRole(Role.PRESIDENT);
        testTarget.setRole(Role.GENERAL);

        memberRepository.save(president);
        memberRepository.save(testTarget);
    }

    @AfterEach
    void afterEach() {
        transactionManager.rollback(status);
    }

    @Test
    @DisplayName("회장_위임_기능")
    void presidentDelegateTest() {
        Member currentPresident = getMember(presidentId);
        Member nextPresident = getMember(testTargetId);

        currentPresident.setRole(Role.GENERAL);
        nextPresident.setRole(Role.PRESIDENT);

        Assertions.assertThat(currentPresident.getRole()).isEqualTo(Role.GENERAL);
        Assertions.assertThat(nextPresident.getRole()).isEqualTo(Role.PRESIDENT);
    }
}
