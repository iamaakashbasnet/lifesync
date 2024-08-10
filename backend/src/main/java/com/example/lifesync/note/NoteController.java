package com.example.lifesync.note;

import com.example.lifesync.token.TokenService;
import com.example.lifesync.user.UserRepository;
import com.example.lifesync.user.UserService;
import com.example.lifesync.utils.UtilFunctions;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.lifesync.user.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/note")
public class NoteController {

    private final NoteService noteService;

    private final TokenService tokenService;

    private final UtilFunctions utilFunctions;

    private final HttpServletRequest httpServletRequest;

    private final UserService userService;

    @GetMapping("/getAllNotes")
    public ResponseEntity<List<Note>> getNotes() {
        String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);
        List<Note> note = noteService.findNoteByUser(user);
        return ResponseEntity.ok(note);
    }

    @GetMapping("/getNote/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Integer id) {
        Note note = noteService.findNoteById(id);
        return ResponseEntity.ok(note);
    }

    @PostMapping("/addNote")
    @Transactional
    public ResponseEntity<Note> addNote(@RequestBody NoteRequestDTO note) {
        String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
        String username = tokenService.extractUsername(token);
        User newuser = userService.findByUsername(username);

        Note newNote = Note.builder()
                .title(note.getTitle())
                .content(note.getContent())
                .user(newuser)
                .build();

        noteService.save(newNote);

        return ResponseEntity.ok(newNote);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Note> updateNote(@RequestBody NoteRequestDTO noteRequest) {
        String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);

        Note existingNote = noteService.findNoteById(noteRequest.getId());

        if (!existingNote.getUser().equals(user)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        existingNote.setTitle(noteRequest.getTitle());
        existingNote.setContent(noteRequest.getContent());

        noteService.save(existingNote);

        return ResponseEntity.ok(existingNote);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteNote(@PathVariable Integer id) {
        String token = utilFunctions.extractTokenFromRequest(httpServletRequest);
        String username = tokenService.extractUsername(token);
        User user = userService.findByUsername(username);

        Note noteToDelete = noteService.findNoteById(id);

        if (!noteToDelete.getUser().equals(user)) {
            return ResponseEntity.status(403).build();
        }

        noteService.delete(noteToDelete);

        return ResponseEntity.noContent().build();
    }

}
