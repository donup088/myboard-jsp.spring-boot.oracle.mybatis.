package com.dong.myboard.mapper;

import com.dong.myboard.domain.BoardVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardMapperTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testGetList() {
        boardMapper.getList().forEach(board -> System.out.println(board));
    }

    @Test
    public void testInsert() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성한 글");
        boardVO.setContent("새로 작성한 내용");
        boardVO.setWriter("user01");

        boardMapper.insert(boardVO);

        System.out.println(boardVO);
    }

    @Test
    public void testRead() {
        //DB에 1번이 있는지 확인
        BoardVO boardVO = boardMapper.read(1L);

        System.out.println(boardVO);
    }

    @Test
    public void testDelete() {
        //DB에 3번이 있는지 확인
        System.out.println("Delete Count: " + boardMapper.delete(3L));
    }

    @Test
    public void testUpdate() {
        //DB에 1번이 있는지 확인
        BoardVO boardVO = new BoardVO();
        boardVO.setBno(1L);
        boardVO.setTitle("수정");
        boardVO.setContent("수정");
        boardVO.setWriter("updatedUser");

        int count = boardMapper.update(boardVO);
        System.out.println("UPDATE COUNT: " + count);
    }
}