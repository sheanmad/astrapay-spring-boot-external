package com.astrapay.repository;

import com.astrapay.entity.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NoteRepository {
    private final List<Note> notes = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Note> findAll(){
        return new ArrayList<>(notes);
    };

    public Optional<Note> findById(Long id){
        return notes.stream().filter(note -> note.getId().equals(id)).findFirst();
    };

    public Optional<Note> findByTitle(String title){
        return notes.stream().filter(note -> note.getTitle().equals(title)).findFirst();
    }

    public Note save(Note note){
        if(note.getId() == null){
            note.setId(idCounter++);
            notes.add(note);
        }
        return note;
    };

    public boolean deleteById(Long id){
        return notes.removeIf(note -> note.getId().equals(id));
    };
}
