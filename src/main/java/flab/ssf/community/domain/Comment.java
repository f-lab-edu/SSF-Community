package flab.ssf.community.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Comment {

    private int id;

    private String content;

    private String uid;

    private int no;

    private Date date;




}
