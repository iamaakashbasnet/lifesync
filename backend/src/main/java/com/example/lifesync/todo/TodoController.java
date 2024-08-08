package com.example.lifesync.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class TodoController {
    @Autowired

    private TodoService todoService;



    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TodoEntity>> getTodosByUserId(@PathVariable Long userId) {
        List<TodoEntity> todos = todoService.getTodosByUserId(userId);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoEntity> getTodoById(@PathVariable Long id) {
        TodoEntity todo = todoService.getTodoById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoEntity> createTodo(@RequestBody TodoEntity todo) {
        TodoEntity createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoEntity> updateTodo(@PathVariable Long id, @RequestBody TodoEntity todoDetails) {
        TodoEntity updatedTodo = todoService.updateTodo(id, todoDetails);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    }

