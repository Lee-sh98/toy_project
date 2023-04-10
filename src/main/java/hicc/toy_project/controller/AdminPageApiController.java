package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.AdminPageRequest;
import hicc.toy_project.controller.dto.MemberResponse;
import hicc.toy_project.service.AdminPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminPageApiController {
    private final AdminPageService adminPageService;

    @GetMapping("/admin/members")
    @ResponseBody
    public List<MemberResponse> members(@RequestBody AdminPageRequest request){
        return adminPageService.members(request.getId());
    }


}
