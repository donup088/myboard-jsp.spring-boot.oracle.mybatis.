package com.dong.myboard.controller;

import com.dong.myboard.domain.BoardVO;
import com.dong.myboard.sevice.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @GetMapping("/list")
    public void list(Model model){
        log.info("list...");

        model.addAttribute("list",boardService.getList());
    }


    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr){
        log.info("register: "+board);

        boardService.register(board);

        rttr.addFlashAttribute("result",board.getBno());

        return "redirect:/board/list";
    }

    @GetMapping("/get")
    public void get(@RequestParam("bno")Long bno, Model model){
        log.info("get...");
        model.addAttribute("board",boardService.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board,RedirectAttributes rttr){
        log.info("modify...."+board);

        if(boardService.modify(board)){
            rttr.addFlashAttribute("result","success");
        }

        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno")Long bno,RedirectAttributes rttr){
        log.info("remove..."+bno);

        if(boardService.remove(bno)){
            rttr.addFlashAttribute("result","success");
        }

        return "redirect:/board/list";
    }
}