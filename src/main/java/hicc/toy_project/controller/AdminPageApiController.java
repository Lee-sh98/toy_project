package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.AdminPageRequest;
import hicc.toy_project.controller.dto.MemberResponse;
import hicc.toy_project.service.AdminPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminPageApiController {
    private final AdminPageService adminPageService;

    /**
     * 모든 회원 조회
     * Body 부분에 들어간 id 값을 가진 멤버가 회장일 경우 모든 회원의 리스트를 반환한다.
     *
     * @param request {"id":[회장의 id]}
     * @return List of Members
     */
    @GetMapping("/admin/members")
    @ResponseBody
    public List<MemberResponse> members(@RequestBody AdminPageRequest request) {
        return adminPageService.members(request.getId());
    }

    /**
     * 회원 정보(등급) 수정
     * Body 부분에 들어간 id 값을 가진 멤버가 회장일 경우 targetId 회원의 등급을 수정한다.
     *
     * @param request {"id":[회장의 id],
     *                "targetId": [수정 대상 id]
     *                "role": [Role]}
     * @return 회원 정보 수정이 완료되면 True, 수정할 수 없으면 False
     */
    @PostMapping("/admin/modify")
    @ResponseBody
    public boolean changeRole(@RequestBody AdminPageRequest request) {
        return adminPageService.changeRole(request);
    }

    /***
     * 회원 제명
     * @param request {"id": [회장의 id],
     *                "targetId": [제명 대상 id]}
     * @return 회원을 제명처리하면 True, 처리할 수 없으면 False
     */
    @PostMapping("/admin/expel")
    @ResponseBody
    public boolean expel(@RequestBody AdminPageRequest request) {
        return adminPageService.expel(request);
    }


    /***
     * 가입 승인 대기 목록 조회
     * @param request {"id":[회장 또는 임원진의 id]}
     * @return 가입 승인 대기중인 회원 리스트
     */
    @GetMapping("/admin/applicants")
    @ResponseBody
    public List<MemberResponse> applicants(@RequestBody AdminPageRequest request) {
        return adminPageService.applicants(request.getId());
    }

    /***
     * 회원 가입 승인 및 거부
     * @param request {"id": [회장 또는 임원진의 id],
     *                "targetId": [승인 대상  id]
     *                "approveRequest": [APPROVE/ REJECT]}
     * @return 회원 승인 또는 거부 처리되면 True, 처리할 수 없으면 False
     */
    @PostMapping("/admin/approve")
    @ResponseBody
    public boolean approve(@RequestBody AdminPageRequest request) {
        return adminPageService.approve(request);
    }

    /***
     * 우산 대여 신청 조회
     * @param request {"id": [회장 또는 임원진의 id]}
     * @return
     */
    @GetMapping("/admin/rental/umbrella/application")
    @ResponseBody
    public void umbrellaApplication(@RequestBody AdminPageRequest request) {
    }

    /***
     * 우산 대여 신청 처리
     */
    @GetMapping("/admin/rental/umbrella/lend")
    @ResponseBody
    public void lendUmbrella() {

    }

}
