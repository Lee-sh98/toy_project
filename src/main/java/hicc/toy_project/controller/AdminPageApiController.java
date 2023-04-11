package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.AdminPageRequest;
import hicc.toy_project.controller.dto.MemberResponse;
import hicc.toy_project.service.AdminPageService;
import lombok.Getter;
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

    @PostMapping("/admin/modify")
    @ResponseBody
    public boolean memberModify(@RequestBody AdminPageRequest request) {
        return adminPageService.memberModify(request);
    }

    @GetMapping("/admin/applicants")
    @ResponseBody
    public List<MemberResponse> applicants(@RequestBody AdminPageRequest request) {
        return adminPageService.applicants(request.getId());
    }
}
