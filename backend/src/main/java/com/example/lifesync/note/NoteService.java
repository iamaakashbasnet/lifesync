package com.example.lifesync.note;


import com.example.lifesync.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public Note findNoteById(int id) {
        return noteRepository.findReferenceById(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public List<Note> findNoteByUser(User user) {
        return noteRepository.findNoteByUser(user);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

}
