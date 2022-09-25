package flab.SSF.Community.domain;

import java.util.Date;

public class Comment {

    private int id;

    private String content;

    private String uid;

    private int no;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", uid='" + uid + '\'' +
                ", date='" + date + '\'' +
                ", no=" + no +
                '}';
    }
}
