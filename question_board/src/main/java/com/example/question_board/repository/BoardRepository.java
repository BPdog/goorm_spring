package com.example.question_board.repository;

import com.example.question_board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BoardRepository extends JpaRepository <Board,Long>{
}
