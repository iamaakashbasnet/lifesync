package com.example.lifesync.note;

import com.example.lifesync.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lifesync.user.User;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findNoteByUser(User user);

    Note findReferenceById(int id);

}
