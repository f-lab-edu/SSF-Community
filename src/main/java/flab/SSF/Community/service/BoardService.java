package flab.SSF.Community.service;

import flab.SSF.Community.Mapper.BoardMapper;
import flab.SSF.Community.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }


    /**
     * 게시글 등록
     */

    public Optional<Board> writeBoard(Board board) {
        boardMapper.insert(board);
        return boardMapper.selectByNo(board.getNo());
    }

    /**
     * 게시글 수정(제목, 내용, 카테고리)
     */
    void updateBoard(Board board,String title, String content, int category) {
        boardMapper.update(board, title, content, category);
    }

    /**
     * 해당 게시글 삭제
     */
    void deleteBoard(Board board) {
        boardMapper.delete(board);
    }

    /**
     * 게시글 조회수 증가
     */
    void updateBoardViews(Board board) {
        boardMapper.updateViews(board);
    }


    /**
     * 게시글 조회수별 정렬
     */
    List<Board> findListbyViews() {
        return boardMapper.orderByViews();
    }

    /**
     * 게시글 제목 검색 정렬
     */
    List<Board> findListbyTitle(String title) {
        return boardMapper.selectByTitle(title);
    }

    /**
     * 게시글 내용 검색 정렬
     */
    List<Board> findListbyContent(String content) {
        return boardMapper.selectByContent(content);
    }

    /**
     * 게시글 카테고리 검색 정렬
     */
    List<Board> findListbyCategory(int category) {
        return boardMapper.selectByCategory(category);
    }

    /**
     * 게시글 번호 검색으로 게시글 찾기
     */
    Optional<Board> findBoardbyNo(int no) {
        return boardMapper.selectByNo(no);
    }



}
