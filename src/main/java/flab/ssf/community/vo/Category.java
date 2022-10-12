package flab.ssf.community.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Category {
    NOTICE(1),
    FREE(2),
    MEETING(3);

    private int number;

}
