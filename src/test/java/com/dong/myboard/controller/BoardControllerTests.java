package com.dong.myboard.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@Log4j2
class BoardControllerTests {

    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andReturn()
                .getModelAndView()
                .getModelMap());
    }

    @Test
    public void testRegister() throws Exception {
        String resultPage = mvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title", "테스트 새글 제목")
                .param("content", "테스트 새글 내용")
                .param("writer", "user00"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testGet() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/get")
                .param("bno", "1"))
                .andReturn().getModelAndView().getModelMap());
    }

    @Test
    public void testModify() throws Exception {
        String resultPage = mvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "1")
                .param("title", "수정된 테스트 새글 제목")
                .param("content", "수정된 테스트 새글 내용")
                .param("writer", "user00"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testRemove() throws Exception {
        String resultPage = mvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("bno", "21"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testListPaging() throws Exception {
        log.info(mvc.perform(MockMvcRequestBuilders.get("/board/list")
        .param("pageNum","1")
        .param("amount","10"))
        .andReturn().getModelAndView().getModelMap());
    }
}