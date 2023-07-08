package hicc.toy_project.mypage;

import hicc.toy_project.controller.dto.MyPageRequest;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import hicc.toy_project.repository.MemberRepository;
import hicc.toy_project.service.MyPageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Transactional
@SpringBootTest
public class MyPageTest {
    @Autowired
    PlatformTransactionManager transactionManager;

    TransactionStatus status;

    @Autowired
    MyPageService myPageService;

    @Autowired
    MemberRepository memberRepository;

    String id = "C011003";

    @BeforeEach
    void beforeEach() {
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        Member testMember = new Member(id, "testName", "010");
        testMember.setRole(Role.GENERAL);
        testMember.setMajor("Computer");

        memberRepository.save(testMember);
    }

    @AfterEach
    void afterEach() {
        transactionManager.rollback(status);
    }

    @Test
    void memberInfoTest() {
        Member foundMember = myPageService.memberInfo(id);
        Assertions.assertThat(foundMember.getIdNumber()).isEqualTo(id);
    }

    @Test
    void withdrawTest() {
        Assertions.assertThat(myPageService.withdraw(id)).isEqualTo(true);
    }

    @Test
    void presidentWithdrawalTest() {
        memberRepository.findByIdNumber(id).map(member ->
                member.updateRole(Role.PRESIDENT)
        );

        Assertions.assertThatThrownBy(() ->
                myPageService.withdraw(id)
        );
    }


}
