package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardReposiory boardReposiory;

    public void write(Board board) {

        boardReposiory.save(board);
    }
}
