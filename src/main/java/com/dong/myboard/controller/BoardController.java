package com.dong.myboard.controller;

import com.dong.myboard.domain.BoardAttachVO;
import com.dong.myboard.domain.BoardVO;
import com.dong.myboard.domain.Criteria;
import com.dong.myboard.domain.PageDTO;
import com.dong.myboard.sevice.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @GetMapping("/list")
    public void list(Criteria cri,Model model){
        log.info("list: "+cri);
        int total=boardService.getTotal(cri);

        model.addAttribute("list",boardService.getList(cri));
        model.addAttribute("pageMaker",new PageDTO(cri,total));
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr){
        log.info("register: "+board);
        if(board.getAttachList()!=null){
            board.getAttachList().forEach(boardAttachVO -> log.info(boardAttachVO));
        }

        boardService.register(board);

        rttr.addFlashAttribute("result",board.getBno());

        return "redirect:/board/list";
    }

    @GetMapping({"/get","/modify"})
    public void get(@RequestParam("bno")Long bno,@ModelAttribute("cri")Criteria cri, Model model){
        log.info("get... or modify...");
        model.addAttribute("board",boardService.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board,@ModelAttribute("cri")Criteria cri, RedirectAttributes rttr){
        log.info("modify...."+board);

        if(boardService.modify(board)){
            rttr.addFlashAttribute("result","success");
        }

        return "redirect:/board/list"+cri.getLink();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno")Long bno,@ModelAttribute("cri")Criteria cri,RedirectAttributes rttr){
        log.info("remove..."+bno);
        List<BoardAttachVO> attachList=boardService.getAttachList(bno);
        if(boardService.remove(bno)){
            deleteFiles(attachList);
            rttr.addFlashAttribute("result","success");
        }
        return "redirect:/board/list"+cri.getLink();
    }

    @GetMapping(value = "/getAttachList",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
        log.info("getAttachList....");
        return new ResponseEntity<>(boardService.getAttachList(bno), HttpStatus.OK);
    }

    private void deleteFiles(List<BoardAttachVO> attachList){
        if(attachList==null||attachList.size()==0){
            return;
        }
        log.info("delete attach files......");
        attachList.forEach(attach->{
            try{
                Path file= Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
                Files.deleteIfExists(file);
                if(Files.probeContentType(file).startsWith("image")){
                    Path thumbNail=Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
                    Files.delete(thumbNail);
                }
            }catch(Exception e){
                log.error("delete file error: "+e.getMessage());
            }
        });
    }
}