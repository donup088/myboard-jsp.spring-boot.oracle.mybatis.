package com.dong.myboard.sevice;

import com.dong.myboard.domain.BoardVO;
import com.dong.myboard.domain.Criteria;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testExist(){
        log.info(boardService);
        assertNotNull(boardService);
    }

    @Test
    public void testRegister(){
        BoardVO boardVO=new BoardVO();
        boardVO.setTitle("새로작성");
        boardVO.setContent("새로운 내용");
        boardVO.setWriter("user!");

        boardService.register(boardVO);
        log.info("생성된 게시물의 번호: "+boardVO.getBno());
    }

    @Test
    public void testGetList(){
        boardService.getList(new Criteria(3,10)).forEach(boardVO -> log.info(boardVO));
    }

    @Test
    public void testGet(){
        log.info(boardService.get(1L));
    }

    @Test
    public void testModify(){
        BoardVO boardVO=boardService.get(1L);
        boardVO.setTitle("수정");
        assertEquals(boardService.modify(boardVO),true);
    }

    @Test
    public void testDelete(){
        //1번있는지 확인후 test
        assertEquals(boardService.remove(1L),true);
    }
}