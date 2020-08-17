package com.dong.myboard.sevice;

import com.dong.myboard.domain.BoardAttachVO;
import com.dong.myboard.domain.BoardVO;
import com.dong.myboard.domain.Criteria;
import com.dong.myboard.mapper.BoardAttachMapper;
import com.dong.myboard.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
    @Setter(onMethod_ ={@Autowired} )
    private BoardMapper boardMapper;

    @Setter(onMethod_ ={@Autowired} )
    private BoardAttachMapper boardAttachMapper;


    @Transactional
    @Override
    public void register(BoardVO boardVO) {
        log.info("register..... "+boardVO);
        boardMapper.insertSelectKey(boardVO);
        if(boardVO.getAttachList()==null||boardVO.getAttachList().size()<=0){
            return;
        }

        boardVO.getAttachList().forEach(attach->{
            attach.setBno(boardVO.getBno());
            boardAttachMapper.insert(attach);
        });
    }

    @Override
    public BoardVO get(Long bno) {
        log.info("get...."+bno);
        return boardMapper.read(bno);
    }

    @Override
    public boolean modify(BoardVO boardVO) {
        log.info("modify "+ boardVO);
        return boardMapper.update(boardVO) == 1;
    }

    @Override
    public boolean remove(Long bno) {
        log.info("remove "+ bno);
        return boardMapper.delete(bno) == 1;
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        log.info("get List with criteria: "+cri);
        return boardMapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("getTotal: "+cri);
        return boardMapper.getTotalCount(cri);
    }

    @Override
    public List<BoardAttachVO> getAttachList(Long bno) {
        log.info("get Attach list by bno "+bno);
        return boardAttachMapper.findByBno(bno);
    }


}
