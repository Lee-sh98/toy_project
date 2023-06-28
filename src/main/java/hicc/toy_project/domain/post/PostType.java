package hicc.toy_project.domain.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostType {
    NOTICE(1000),
    FREE(1000),
    ACTIVITY(1000),
    PREVIOUS_TEST(1000),
    EMPLOYMENT(1000),
    GRADUATE(1000),
    MUST_EAT(100);

    private final int contentLength;
}
