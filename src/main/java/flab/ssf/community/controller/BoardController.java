package flab.ssf.community.controller;

import flab.ssf.community.domain.Board;
import flab.ssf.community.service.BoardService;
import flab.ssf.community.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/boards")
    public String noticeBoardList(Model model,HttpSession session,Category category) {
        List<Board> boards= boardService.findListbyCategory(category.getNumber());
        model.addAttribute("boards",boards);
        model.addAttribute("session",session.getAttribute("user"));
        return "boards/BoardList";
    }



    @GetMapping("/boards/update")
    public String loadExistingBoard(BoardForm boardForm,Model model) {
        model.addAttribute("board",boardService.findBoardbyNo(boardForm.getNo()));
        return "boards/loadExcistingBoard";
    }

    @PostMapping("/boards/update")
    public String updateBoard(BoardForm boardForm) {
        Board board = new Board();
        board.setNo(boardForm.getNo());
        boardService.updateBoard(board,boardForm.getTitle(),
                boardForm.getContent(),boardForm.getCategory());
        return "boards/category";
    }

    @GetMapping("boards/delete")
    public String deleteBoard(BoardForm boardForm) {
        boardService.deleteBoard(boardForm.getNo());
        return "boards/category";
    }

    @GetMapping("/boards/write")
    public String writeBoard(HttpSession session,Model model) {
        String uid=(String)session.getAttribute("user");
        model.addAttribute("uid", uid);
        return "boards/writeBoard";
    }

    @PostMapping("/boards/write")
    public String insertBoard(BoardForm boardForm,HttpSession session) {
        Board board = new Board();
        board.setUid((String)session.getAttribute("user"));
        board.setCategory(boardForm.getCategory());
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        boardService.writeBoard(board);
        return "boards/category";
    }

    @GetMapping("/boards/show")
    public String showBoard(BoardForm boardForm, Model model, HttpServletRequest request,HttpServletResponse response) {
        Board board=boardService.findBoardbyNo(boardForm.getNo()).get();
        viewsInspection(request,response,board);
        model.addAttribute("board", board);
        return "boards/showBoard";

    }

    public void viewsInspection(HttpServletRequest request, HttpServletResponse response,Board board) {
        Cookie[] cookies = request.getCookies();
        Boolean isExisted=false;
        for (Cookie cookie: cookies) {
            if (cookie.getValue()==Integer.toString(board.getNo())) {
                isExisted=true;
                break;
            }
        }

        if(isExisted==false) {
            Cookie cookie= new Cookie("seeBoardNumber",Integer.toString(board.getNo()));

            response.addCookie(cookie);
            boardService.updateBoardViews(board);
        }
    }


}
