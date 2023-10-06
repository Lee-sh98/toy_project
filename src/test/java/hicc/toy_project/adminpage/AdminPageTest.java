package hicc.toy_project.adminpage;


import hicc.toy_project.controller.dto.AdminPageRequest;
import hicc.toy_project.controller.dto.ApproveRequest;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import hicc.toy_project.exception.CustomException;
import hicc.toy_project.exception.ErrorCode;
import hicc.toy_project.repository.MemberRepository;
import hicc.toy_project.service.AdminPageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static org.assertj.core.api.Assertions.*;

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
    String testMemberId = "C011004";

    Member getMember(String id) {
        return memberRepository.findByIdNumber(id)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.MEMBER_NOT_FOUND)
                );
    }

    @BeforeEach
    void beforeEach() {
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        Member president = Member.builder()
                .id(presidentId)
                .nickName("testPresident")
                .phoneNumber("010")
                .role(Role.PRESIDENT)
                .build();

        Member testTarget = Member.builder()
                .id(testMemberId)
                .nickName("testTarget")
                .phoneNumber("011")
                .role(Role.GENERAL)
                .build();

        memberRepository.save(president);
        memberRepository.save(testTarget);
    }

    @AfterEach
    void afterEach() {
        transactionManager.rollback(status);
    }

    @Nested
    @DisplayName("회장 위임 테스트")
    class delegateTest {
        @Test
        @DisplayName("회장 권한 위임 후 기존 회장은 일반 회원이 된다.")
        void success() {
            Member currentPresident = getMember(presidentId);
            Member nextPresident = getMember(testMemberId);

            AdminPageRequest request = AdminPageRequest.builder()
                    .id(currentPresident.getIdNumber())
                    .targetId(nextPresident.getIdNumber())
                    .build();

            adminPageService.delegate(request);

            assertThat(currentPresident.getRole())
                    .isEqualTo(Role.GENERAL);

            assertThat(nextPresident.getRole())
                    .isEqualTo(Role.PRESIDENT);
        }
    }


    @Nested
    @DisplayName("회원 등급 수정 테스트")
    class changeRoleTest {

        @Test
        @DisplayName("정상적으로 회원 등급을 임원으로 수정한다.")
        void success() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(testMemberId)
                    .role(Role.EXECUTIVE)
                    .build();

            adminPageService.changeRole(request);

            assertThat(getMember(testMemberId).getRole())
                    .isEqualTo(Role.EXECUTIVE);
        }

        @Test
        @DisplayName("회장 본인의 등급을 수정시 발생하는 예외 테스트")
        void selfUpdateException() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(presidentId)  // 타겟 id를 회장으로 설정
                    .role(Role.EXECUTIVE)
                    .build();

            assertThatThrownBy(() -> adminPageService
                    .changeRole(request))
                    .isInstanceOf(CustomException.class);
        }

        @Test
        @DisplayName("회장으로 직책을 변경시 발생하는 예외 테스트")
        void updateIntoPresidentException() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(testMemberId)
                    .role(Role.PRESIDENT)
                    .build();

            assertThatThrownBy(() -> adminPageService
                    .changeRole(request))
                    .isInstanceOf(CustomException.class);
        }

        @Test
        @DisplayName("게스트로 등급을 변경시 발생하는 예외 테스트")
        void updateIntoGuestException() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(testMemberId)
                    .role(Role.GENERAL)
                    .build();

            assertThatThrownBy(() -> adminPageService
                    .changeRole(request))
                    .isInstanceOf(CustomException.class);
        }

        @Test
        @DisplayName("같은 등급으로 변경시 발생하는 예외 테스트")
        void updateIntoSameRoleException() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(testMemberId)
                    .role(Role.GENERAL)
                    .build();

            assertThatThrownBy(() -> adminPageService
                    .changeRole(request))
                    .isInstanceOf(CustomException.class);
        }
    }

    @Nested
    @DisplayName("회원 제명 테스트")
    class expelTest {

        @Test
        @DisplayName("성공적으로 일반 회원을 제명한다.")
        void success() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(testMemberId)
                    .build();

            assertThat(adminPageService.expel(request))
                    .isEqualTo(true);
        }

        @Test
        @DisplayName("회장 본인을 제명할 때 발생하는 예외 테스트")
        void selfExpelException() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(presidentId)  // 타겟 id를 회장으로 설정
                    .build();

            assertThatThrownBy(() -> adminPageService
                    .expel(request))
                    .isInstanceOf(CustomException.class);
        }

    }

    @Nested
    @DisplayName("회원 가입 승인 테스트")
    class approveTest {
        private final String testApplicantId = "C011005";

        @BeforeEach
        void beforeEach() {
            Member applicant = Member.builder()
                    .id(testApplicantId)
                    .nickName("targetApplicant")
                    .phoneNumber("012")
                    .role(Role.GUEST)
                    .build();

            memberRepository.save(applicant);
        }


        @Test
        @DisplayName("지원자를 승인한다.")
        void successApproval() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(testApplicantId)
                    .approveRequest(ApproveRequest.APPROVE)
                    .build();

            adminPageService.approve(request);

            assertThat(getMember(testApplicantId).getRole())
                    .isEqualTo(Role.GENERAL);

        }

        @Test
        @DisplayName("지원자를 반려한다.")
        void successRejection() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(testApplicantId)
                    .approveRequest(ApproveRequest.REJECT)
                    .build();

            assertThat(adminPageService.approve(request))
                    .isEqualTo(true);
        }

        @Test
        @DisplayName("타겟 id가 게스트가 아닐 때 발생하는 예외 테스트")
        void approveNotGuestException() {
            AdminPageRequest request = AdminPageRequest.builder()
                    .id(presidentId)
                    .targetId(testMemberId) // 지원자가 아닌 일반 회원 id를 설정
                    .build();

            assertThatThrownBy(() -> adminPageService
                    .approve(request))
                    .isInstanceOf(CustomException.class);
        }
    }
}
