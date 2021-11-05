package com.example.freeboard.controller;


import java.io.File;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.freeboard.dto.BoardListDto;
import com.example.freeboard.service.BoardService;

@Controller
public class BoardController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    //자유 게시판
    @GetMapping("/board")
    public String getBoard(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "1") int page) {
        int start = (page - 1) * BoardService.LIMIT;
        List<BoardListDto> boardList = boardService.getListInfo(start);     //조회한 게시물 리스트
        int totalListCount = boardService.getListCount();
        boardService.pagination(modelMap, page, totalListCount);            //페이징
        modelMap.addAttribute("boardList", boardList);
        modelMap.addAttribute("type", "board");
        modelMap.addAttribute("keyword", "null");
        return "board";
    }


    //글쓰기
    @GetMapping("/write")
    public String getWrite() {
        return "write";
    }


    //검색
    @GetMapping("/search")
    public String searchData(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "1") int page,
                             @RequestParam("searchKeyword") String searchKeyword) {
        logger.debug("검색 키워드: " + searchKeyword);
        int start = (page - 1) * BoardService.LIMIT;
        List<BoardListDto> searchList = boardService.getSearchListInfo(searchKeyword, start);
        int totalListCount = boardService.getSearchListCount(searchKeyword);
        boardService.pagination(modelMap, page, totalListCount);
        modelMap.addAttribute("boardList", searchList);
        modelMap.addAttribute("type", "search");
        modelMap.addAttribute("keyword", searchKeyword);
        return "board";
    }


    //하드 용량 테스트 (배포 되었을때)
    @ResponseBody
    @GetMapping("/check")
    public String check() {
        String path1 = "tomcat";
        String path2 = "boardImgFile";
        String path3 = "reserveImgFile";
        long size1 = FileUtils.sizeOfDirectory(new File(path1));
        long size2 = FileUtils.sizeOfDirectory(new File(path2));
        long size3 = FileUtils.sizeOfDirectory(new File(path3));
        return Long.toString(size1 + size2 + size3);
    }


}
