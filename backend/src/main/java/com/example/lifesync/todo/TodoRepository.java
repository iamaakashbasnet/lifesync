package com.example.lifesync.todo;

import com.example.lifesync.note.Note;
import com.example.lifesync.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findTodoByUser(User user);

    TodoEntity findReferenceById(int id);

//    List<TodoEntity> findByUserId(Long userId);
}