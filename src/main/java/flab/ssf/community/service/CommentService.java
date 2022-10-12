package flab.ssf.community.service;

import flab.ssf.community.domain.Comment;
import flab.ssf.community.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }


    /**
     * 댓글 입력
     */
    public void insertComment(Comment comment) {
        commentMapper.insert(comment);
    }

    /**
     * 댓글 수정
     */
    public void updateComment(Comment comment, String content) {
        commentMapper.update(comment, content);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Comment comment) {
        commentMapper.delete(comment);
    }

    public void deleteCommentbyNo(int no) {
        commentMapper.deletebyNo(no);
    }

    public void deleteCommentbyUid(String uid) {
        commentMapper.deletebyUid(uid);
    }

    public Comment findOne(int id) {
        return commentMapper.findById(id);
    }

    public List<Comment> findAllCommentByUid(String uid) {
        List<Comment> list=commentMapper.findAllByUid(uid);
        return list;
    }

    public List<Comment> findAllCommentByNo(int no) {
        List<Comment> list=commentMapper.findAllByNo(no);
        return list;
    }

    /**
     * 댓글 불러오기
     */
    public List<Comment> findAllComment() {
        List<Comment> list=commentMapper.findAll();
        return list;
    }
}
