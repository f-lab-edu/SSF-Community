package flab.SSF.Community.Mapper;


import flab.SSF.Community.domain.Comment;
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
