package com.example.lifesync.note;

import com.example.lifesync.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/note")
public class NoteController {
    private final NoteService noteService;
    private final UserRepository userRepository;

//    @GetMapping("/getAllNotes")
//    public List<Note> getNotes() {
//        return ResponseEntity.ok(noteService.findNoteByUser(user));
//    }
}
