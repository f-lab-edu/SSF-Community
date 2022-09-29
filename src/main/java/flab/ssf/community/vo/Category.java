package flab.ssf.community.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    NOTICE(1),
    FREE(2),
    METTING(3);

    private int number;


}
