package com.example.freeboard.serviceImpl;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


import com.example.freeboard.dao.BoardDao;
import com.example.freeboard.dto.BoardListDto;
import com.example.freeboard.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    // 게시글 목록
    @Override
    public List<BoardListDto> getListInfo(int start) {
        return boardDao.getBoardList(start, LIMIT);
    }

    // 총 게시글 수
    @Override
    public int getListCount() {
        return boardDao.getListCount();
    }

    // 페이징 처리
    @Override
    public void pagination(ModelMap modelMap, int page, int totalListCount) {
        // 리스트의 시작튜플, 총 게시물 수, 유형(board or search)
        int listCount = BoardService.LIMIT; // 한 화면에 보여질 게시물 수
        int totalPage = totalListCount / listCount; // 총 페이지 수
        int pageItemsCount = 5; // 한 화면에 보여질 총 페이지 요소 개수
        int block = (page - 1) / pageItemsCount; // 현재 블록 (첫 블록 = 0)
        if (totalListCount % listCount > 0) {
            totalPage++;
        }

        List<Integer> pageList = pageListing(block, pageItemsCount, totalPage);
        int preArrowPage = (block - 1) * pageItemsCount + 1; // 이전 블록 첫 페이지
        int nextArrowPage = (block + 1) * pageItemsCount + 1; // 다음 블록 첫 페이지

        modelMap.addAttribute("listCount", listCount);
        modelMap.addAttribute("pageList", pageList);
        modelMap.addAttribute("preArrowPage", preArrowPage);
        modelMap.addAttribute("nextArrowPage", nextArrowPage);
        modelMap.addAttribute("totalPage", totalPage);


    }

    //페이지 번호 목록 처리
    public List<Integer> pageListing(int block, int pageItemsCount, int totalPage) {
        List<Integer> pageList = new ArrayList<>(); // 페이지 번호들을 담을 리스트
        int pageItem = block * pageItemsCount + 1;
        for (int i = pageItem; i < pageItem + pageItemsCount; i++) {
            if (i > totalPage)
                break;
            pageList.add(i);
        }
        return pageList;
    }

    // 검색 리스트
    @Override
    public List<BoardListDto> getSearchListInfo(String searchKeyword, int start) {
        return boardDao.getSearchListInfo(searchKeyword, start, LIMIT);
    }

    // 검색 리스트 수
    @Override
    public int getSearchListCount(String searchKeyword) {
        return boardDao.getSearchListCount(searchKeyword);
    }


}
