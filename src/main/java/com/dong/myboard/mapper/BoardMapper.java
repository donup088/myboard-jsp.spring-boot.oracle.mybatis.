package com.dong.myboard.mapper;

import com.dong.myboard.domain.BoardVO;
import com.dong.myboard.domain.Criteria;

import java.util.List;

public interface BoardMapper {
    public List<BoardVO> getList();

    public List<BoardVO> getListWithPaging(Criteria cri);

    public void insert(BoardVO boardVO);

    public void insertSelectKey(BoardVO boardVO);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    public int update(BoardVO boardVO);

    public int getTotalCount(Criteria cri);
}
