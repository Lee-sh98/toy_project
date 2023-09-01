package hicc.toy_project.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public record ApproveResponse(ApproveStatus status) {
}
