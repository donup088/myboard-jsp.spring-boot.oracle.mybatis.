package com.dong.myboard.controller;

import com.dong.myboard.domain.Criteria;
import com.dong.myboard.domain.ReplyPageDTO;
import com.dong.myboard.domain.ReplyVO;
import com.dong.myboard.sevice.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/replies")
@AllArgsConstructor
public class ReplyController {
    private ReplyService replyService;

    @PostMapping(value = "/new",consumes ="application/json" ,produces = MediaType.TEXT_PLAIN_VALUE )
    public ResponseEntity<String> create(@RequestBody ReplyVO reply){
        log.info("new Register: "+reply);

        int insertCount=replyService.register(reply);

        return insertCount==1
                ?new ResponseEntity<>("success", HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/pages/{bno}/{page}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page")int page, @PathVariable("bno")Long bno){
        log.info("getList........");
        Criteria cri=new Criteria(page,10);

        return new ResponseEntity<>(replyService.getListPage(cri,bno),HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplyVO> get(@PathVariable("rno")Long rno){
        log.info("get......");
        return new ResponseEntity<>(replyService.get(rno),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("rno")Long rno){
        log.info("remove......");
        return replyService.remove(rno)==1
                ?new ResponseEntity<>("success",HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = {RequestMethod.PATCH,RequestMethod.PUT},value = "/{rno}"
            ,consumes = "application/json",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody ReplyVO reply,@PathVariable("rno")Long rno){
        log.info("modify......");
        reply.setRno(rno);

        return replyService.modify(reply)==1
                ?new ResponseEntity<>("success",HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
