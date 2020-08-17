package com.dong.myboard.sevice;

import com.dong.myboard.domain.BoardAttachVO;
import com.dong.myboard.domain.BoardVO;
import com.dong.myboard.domain.Criteria;

import java.util.List;

public interface BoardService {
    public void register(BoardVO board);

    public BoardVO get(Long bno);

    public boolean modify(BoardVO board);

    public boolean remove(Long bno);

    public List<BoardVO> getList(Criteria cri);

    public int getTotal(Criteria cri);

    public List<BoardAttachVO> getAttachList(Long bno);
}
