package com.example.lifesync.todo;

import com.example.lifesync.note.Note;
import com.example.lifesync.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

//    public List<TodoEntity> getTodosByUserId(Long userId) {
//        return todoRepository.findByUserId(userId);
//    }
public TodoEntity findNoteById(int id) {
    return todoRepository.findReferenceById(id);
}
public TodoEntity save(TodoEntity note) {
    return todoRepository.save(note);
}

    public List<TodoEntity> findTodoByUser(User user) {
        return todoRepository.findTodoByUser(user);
    }


    public Optional<TodoEntity> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public TodoEntity createTodo(TodoEntity todo) {
        return todoRepository.save(todo);
    }

    public TodoEntity updateTodo(Long id, TodoEntity todoDetails) {
        TodoEntity todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setIsCompleted(todoDetails.getIsCompleted());
        return todoRepository.save(todo);
    }

//    public void deleteTodo(Long id) {
//        TodoEntity todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
//        todoRepository.delete(todo);
//    }
public void delete(TodoEntity todo) {
    todoRepository.delete(todo);
}
}