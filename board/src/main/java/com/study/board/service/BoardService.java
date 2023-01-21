package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardReposiory boardReposiory;

    public void write(Board board) {

        boardReposiory.save(board);
    }

    public List<Board> boardList() {
        return boardReposiory.findAll();
    }

    public void saveTestData() {

        for (int i = 0; i < 120; i++) {
            Board board = new Board();

            board.setTitle("제목" + (i + 1));
            board.setContent("내용" + (i + 1));
            boardReposiory.save(board);

            //board.setId(null);
        }
    }
}
