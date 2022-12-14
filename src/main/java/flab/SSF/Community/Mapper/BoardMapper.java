package flab.SSF.Community.Mapper;

import flab.SSF.Community.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface BoardMapper {
    // 게시글 등록
    void insert(Board board);

    //게시글 삭제
    void delete(Board board);

    //게시글 수정
    void update(@Param("board")Board board, @Param("title") String title, @Param("content") String content);

    //게시글 전체목록
    List<Board> findAll();


    //게시글 조회수별 조회
    List<Board> orderByViews();

    //게시글 작성자로 조회하기
    List<Board> selectByUid(String uid);

    //게시글 글 제목으로 조회하기
    List<Board> selectByTitle(String title);

    //게시글 글 내용으로 조회하기
    List<Board> selectByContent(String content);

    //게시글 카테고리 별로 조회하기
    List<Board> selectByCategory(int category);

}
