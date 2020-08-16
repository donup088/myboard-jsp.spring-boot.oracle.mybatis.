package com.dong.myboard.sevice;

import com.dong.myboard.domain.Criteria;
import com.dong.myboard.domain.ReplyVO;

import java.util.List;

public interface ReplyService {
    public int register(ReplyVO reply);

    public ReplyVO get(Long rno);

    public int modify(ReplyVO reply);

    public int remove(Long rno);

    public List<ReplyVO> getList(Criteria cri,Long bno);
}
