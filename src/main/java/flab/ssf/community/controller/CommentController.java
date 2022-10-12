package flab.ssf.community.controller;

import flab.ssf.community.domain.Comment;
import flab.ssf.community.form.CommentForm;
import flab.ssf.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PutMapping
    public String updateComment(CommentForm commentForm, Model model,HttpSession session) {
        Comment comment= new Comment();
        comment.setId(commentForm.getId());
        commentService.updateComment(comment,commentForm.getContent());
        model.addAttribute("comments",commentService.findAllCommentByNo(commentForm.getNo()));
        model.addAttribute("user", session.getAttribute("user"));
        return "boards/loadBoard :: #commentTable";
    }

    @DeleteMapping
    public String deleteComment(int id, int no,Model model,HttpSession session) {
        Comment comment= new Comment();
        comment.setId(id);
        commentService.deleteComment(comment);
        model.addAttribute("comments",commentService.findAllCommentByNo(no));
        model.addAttribute("user", session.getAttribute("user"));
        return "boards/loadBoard :: #commentTable";
    }

    @PostMapping
    public String InsertNewComment(CommentForm commentForm,Model model,HttpSession session) {
        Comment comment = new Comment();
        comment.setContent(commentForm.getContent());
        comment.setNo(commentForm.getNo());
        comment.setUid(commentForm.getUid());
        commentService.insertComment(comment);
        model.addAttribute("comments",commentService.findAllCommentByNo(commentForm.getNo()));
        model.addAttribute("user", session.getAttribute("user"));
        return "boards/loadBoard :: #commentTable";
    }

    @GetMapping
    public String takeCommentList(int no, Model model, HttpSession session) {
        model.addAttribute("comments", commentService.findAllCommentByNo(no));
        model.addAttribute("user", session.getAttribute("user"));
        return "boards/loadBoard :: #commentTable";

    }

}
