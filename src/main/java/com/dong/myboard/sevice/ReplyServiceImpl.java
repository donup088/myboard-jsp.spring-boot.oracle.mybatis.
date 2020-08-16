package com.dong.myboard.sevice;

import com.dong.myboard.domain.Criteria;
import com.dong.myboard.domain.ReplyVO;
import com.dong.myboard.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private ReplyMapper replyMapper;

    @Override
    public int register(ReplyVO reply) {
        log.info("register...."+reply);
        return replyMapper.insert(reply);
    }

    @Override
    public ReplyVO get(Long rno) {
        log.info("get...."+rno);
        return replyMapper.read(rno);
    }

    @Override
    public int modify(ReplyVO reply) {
        log.info("modify...."+reply);
        return replyMapper.update(reply);
    }

    @Override
    public int remove(Long rno) {
        log.info("remove...."+rno);
        return replyMapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        log.info("getList...."+bno);
        return replyMapper.getListWithPaging(cri,bno);
    }
}