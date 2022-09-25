package flab.SSF.Community.Mapper;

import flab.SSF.Community.domain.Board;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class BoardMapperTest {

    @Autowired
    public BoardMapper boardMapper;

    @Test
    void insert() {
        Board board = new Board();
        board.setTitle("컴퓨터 과학");
        board.setContent("재밌다");
        board.setCategory(1);
        board.setUid("ktf1686");

        boardMapper.insert(board);
        System.out.println(boardMapper.findAll());
    }
}