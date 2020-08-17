package com.dong.myboard.mapper;

import com.dong.myboard.domain.BoardAttachVO;

import java.util.List;

public interface BoardAttachMapper {
    public void insert(BoardAttachVO boardAttach);

    public void delete(Long uuid);

    public List<BoardAttachVO> findByBno(Long bno);

    public void deleteAll(Long bno);
}
