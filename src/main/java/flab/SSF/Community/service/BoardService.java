package flab.SSF.Community.service;

import flab.SSF.Community.Mapper.BoardMapper;
import flab.SSF.Community.domain.Board;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    private BoardMapper boardMapper;

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

    public Optional<Board> updateBoard(Board board,String title, String content, int category) {
        boardMapper.update(board, title, content, category);
        return
    }


}
