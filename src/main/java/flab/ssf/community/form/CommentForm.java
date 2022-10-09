package flab.ssf.community.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentForm {

    private String content;

    private String uid;

    private int no;

    private Date date;
}
