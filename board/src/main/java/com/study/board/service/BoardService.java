package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardReposiory boardReposiory;

    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws IOException {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";  //파일 경로

        UUID uuid = UUID.randomUUID();  //랜덤으로 이름 생성
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);  //파일을 생성해서 projectPath 경로에 fileName의 이름으로 저장
        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);    //서버에서 접근을 할 때는 static 밑에 있는 부분은 아래 경로로만 접근 가능
        
        boardReposiory.save(board);
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {
        return boardReposiory.findAll(pageable);
    }

    // 테스트 데이터 저장
    public void saveTestData() {

        for (int i = 0; i < 120; i++) {
            Board board = new Board();

            board.setTitle("제목" + (i + 1));
            board.setContent("내용" + (i + 1));
            boardReposiory.save(board);

            //board.setId(null);
        }
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardReposiory.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {

        boardReposiory.deleteById(id);
    }
}
