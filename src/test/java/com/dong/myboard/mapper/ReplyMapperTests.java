package com.dong.myboard.mapper;

import com.dong.myboard.domain.Criteria;
import com.dong.myboard.domain.ReplyVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ReplyMapperTests {
    @Setter(onMethod_ ={@Autowired} )
    private ReplyMapper replyMapper;

    @Test
    public void insert(){
        ReplyVO reply=new ReplyVO();
        reply.setBno(1301L);
        reply.setReply("댓글 테스트 두번째");
        reply.setReplyer("Dong");

        replyMapper.insert(reply);
    }

    @Test
    public void read(){
        log.info(replyMapper.read(1L));
    }

    @Test
    public void delete(){
        replyMapper.delete(2L);
    }

    @Test
    public void update(){
        ReplyVO replyVO=replyMapper.read(1L);
        replyVO.setReply("수정된 reply");
        log.info("UPDATE COUNT: "+replyMapper.update(replyVO));
    }

    @Test
    public void getListWithPaging(){
        Criteria cri=new Criteria();
        List<ReplyVO> list=replyMapper.getListWithPaging(cri,1301L);
        list.forEach(reply -> log.info(reply));
    }
}