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
     * Body 부분에 들어간 id 값을 가진 멤버가 회장일 경우 targetId 회원의 등급을 수정한다.
     *
     * @param request {"id":[회장의 id],
     *                "targetId": [수정 대상 id],
     *                "role": [Role]}
     * @return 회원 정보 수정이 완료되면 True, 수정할 수 없으면 False
     */
    @PostMapping("/admin/modify")
    @ResponseBody
    public boolean changeRole(@RequestBody AdminPageRequest request) {
        return adminPageService.changeRole(request);
    }

    @GetMapping("/admin/applicants")
    @ResponseBody
    public List<MemberResponse> applicants(@RequestBody AdminPageRequest request) {
        return adminPageService.applicants(request.getId());
    }

    /***
     *
     * @param request{"id": [회장의 id],
     *               "targetId": [승인 대상  id]
     *               "applyRequest": [APPROVED/ REJECTED]}
     * @return 회원 승인 또는 반려 처리되면 True, 처리할 수 없으면 False
     */
    @PostMapping("/admin/approve")
    @ResponseBody
    public boolean approve(@RequestBody AdminPageRequest request) {
        return adminPageService.approve(request);
    }
}
