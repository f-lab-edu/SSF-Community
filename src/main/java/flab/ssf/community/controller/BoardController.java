package flab.ssf.community.controller;

import flab.ssf.community.Utils.ScriptUtils;
import flab.ssf.community.domain.Board;
import flab.ssf.community.service.BoardService;
import flab.ssf.community.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/boards")
@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping
    public String BoardList(Model model,HttpSession session,Category category,HttpServletRequest request) {
        Map<String, ?> inputFlashMap=RequestContextUtils.getInputFlashMap(request);
        if(inputFlashMap!=null) {
            category=(Category) inputFlashMap.get("category");
        }
        List<Board> boards= boardService.findListbyCategory(category.getNumber());
        model.addAttribute("boards",boards);
        model.addAttribute("category", category);
        model.addAttribute("user",session.getAttribute("user"));
        return "boards/BoardList";
    }

    @GetMapping("/new")
    public String createBoardForm() {
        return "boards/createBoardForm";
    }

    @PostMapping("/new")
    public String registrationBoard(Category checkedcategory, BoardForm boardForm, HttpSession session, RedirectAttributes redirect) {
        Board board = new Board();
        board.setCategory(checkedcategory.getNumber());
        board.setUid((String)session.getAttribute("user"));
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        boardService.writeBoard(board);
        redirect.addFlashAttribute("category",checkedcategory);
        return "redirect:/boards";
    }




    @GetMapping("/update")
    public String loadExistingBoard(BoardForm boardForm,Model model) {
        model.addAttribute("board",boardService.findBoardbyNo(boardForm.getNo()).get());
        return "boards/loadExcistingBoard";
    }

    @PutMapping
    public void updateBoard(BoardForm boardForm,Category checkedcategory, HttpServletResponse response) {
        Board board = new Board();
        board.setNo(boardForm.getNo());
        boardService.updateBoard(board,boardForm.getTitle(),boardForm.getContent(),checkedcategory.getNumber());
        try {
            ScriptUtils.alertAndMovePage(response,"게시글 수정이 완료되었습니다.","/boards?category="+checkedcategory);
        } catch (IOException io) {
            io.getMessage();
        }
    }

    @DeleteMapping
    public void deleteBoard(BoardForm boardForm,Category checkedCategory, HttpServletResponse response) {
        boardService.deleteBoard(boardForm.getNo());
        try {
            ScriptUtils.alertAndMovePage(response,"게시글 삭제가 완료되었습니다.","/boards?category="+checkedCategory);
        } catch (IOException io) {
            io.getMessage();
        }
    }

    @GetMapping("/getBoard")
    public String getBoard(BoardForm boardForm, Model model,HttpServletRequest request,HttpServletResponse response) {
        Board board = boardService.findBoardbyNo(boardForm.getNo()).get();
        viewsInspection(request.getCookies(), response, board);
        model.addAttribute("board", board);
        return "boards/loadBoard";
    }



    public void viewsInspection(Cookie[] cookies, HttpServletResponse response, Board board) {
        Boolean isExisted=false;
        for (Cookie cookie: cookies) {
            if (cookie.getValue().equals(Integer.toString(board.getNo()))) {
                isExisted=true;
                break;
            }
        }

        if(!isExisted) {
            Cookie cookie= new Cookie("seeBoardNumber",Integer.toString(board.getNo()));
            cookie.setMaxAge(7*24*60*60); // 쿠키 생성주기 : 일주일(7day*24hour*60minutes*60seconds)
            response.addCookie(cookie);
            boardService.updateBoardViews(board);
        }
    }


}
