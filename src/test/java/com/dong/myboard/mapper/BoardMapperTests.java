package com.dong.myboard.mapper;

import com.dong.myboard.domain.BoardVO;
import com.dong.myboard.domain.Criteria;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardMapperTests {

    @Setter(onMethod_ ={@Autowired} )
    private BoardMapper boardMapper;

    @Test
    public void testGetList() {
        boardMapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testInsert() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성한 글");
        boardVO.setContent("새로 작성한 내용");
        boardVO.setWriter("user01");

        boardMapper.insert(boardVO);

        log.info(boardVO);
    }

    @Test
    public void testRead() {
        //DB에 1번이 있는지 확인
        BoardVO boardVO = boardMapper.read(1L);

        log.info(boardVO);
    }

    @Test
    public void testDelete() {
        //DB에 3번이 있는지 확인
       log.info("Delete Count: " + boardMapper.delete(3L));
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
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testPaging(){
        Criteria cri=new Criteria();
        cri.setPageNum(2);
        cri.setAmount(20);
        List<BoardVO> list=boardMapper.getListWithPaging(cri);
        list.forEach(boardVO -> log.info(boardVO));
    }

    @Test
    public void testSearch(){
        Criteria cri=new Criteria();
        cri.setKeyword("별");
        cri.setType("TC");
        List<BoardVO> list= boardMapper.getListWithPaging(cri);
        list.forEach(boardVO -> log.info(boardVO));
    }
}