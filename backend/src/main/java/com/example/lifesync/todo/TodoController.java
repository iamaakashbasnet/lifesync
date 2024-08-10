package com.example.lifesync.todo;

import com.example.lifesync.note.Note;
import com.example.lifesync.token.TokenService;
import com.example.lifesync.user.User;
import com.example.lifesync.user.UserService;
import com.example.lifesync.utils.UtilFunctions;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired

    private final TodoService todoService;

    private final TokenService tokenService;


    private final UtilFunctions utilFunctions;

    private final UserService userService;
    private final HttpServletRequest httpServletRequest;

    @GetMapping("/user")
    public ResponseEntity<List<TodoEntity>> getTodos() {
        String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);
        List<TodoEntity> todos = todoService.findTodoByUser(user);

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping("/addTodo")
    @Transactional
    public ResponseEntity<TodoEntity> createTodo(@RequestBody TodoRequestDTO todo) {
        String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
        String username = tokenService.extractUsername(token);
        User newuser = userService.findByUsername(username);

        TodoEntity newTodo = TodoEntity.builder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .user(newuser)
                .build();
        todoService.save(newTodo);
        return ResponseEntity.ok(newTodo);

    }

@PutMapping("/update")
@Transactional
public ResponseEntity<TodoEntity> updateNote(@RequestBody TodoRequestDTO noteRequest) {
    String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
    String username = tokenService.extractUsername(token);
    User user = userService.findByUsername(username);

    TodoEntity existingTodo = todoService.findNoteById(noteRequest.getId());

    if (!existingTodo.getUser().equals(user)) {
        return ResponseEntity.status(403).build(); // Forbidden
    }

    existingTodo.setTitle(noteRequest.getTitle());
    existingTodo.setDescription(noteRequest.getDescription());

    todoService.save(existingTodo);

    return ResponseEntity.ok(existingTodo);
}

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteNote(@PathVariable Integer id) {
        String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);

        TodoEntity todoToDelete = todoService.findNoteById(id);

        if (!todoToDelete.getUser().equals(user)) {
            return ResponseEntity.status(403).build();
        }

        todoService.delete(todoToDelete);

        return ResponseEntity.noContent().build();
    }
    }

