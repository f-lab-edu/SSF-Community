package flab.ssf.community.mapper;


import flab.ssf.community.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    void insert(Comment comment);

    void delete(Comment comment);

    void update(@Param("comment") Comment comment, @Param("content") String content);

    List<Comment> findbyNo(Comment comment);
}
