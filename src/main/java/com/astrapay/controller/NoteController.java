package com.astrapay.controller;

import com.astrapay.dto.APIResponse;
import com.astrapay.dto.NoteDto;
import com.astrapay.dto.NoteRequestDto;
import com.astrapay.service.NoteService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@Api(value = "NoteController")
@Slf4j
@RequestMapping("/v1/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<NoteDto>>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<NoteDto>> getNoteById(@PathVariable Long id){
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse<NoteDto>> addNote(@Valid @RequestBody NoteRequestDto request){
        return new ResponseEntity<>(noteService.addNote(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<NoteDto>> updateNote(@Valid @RequestBody NoteRequestDto request, @PathVariable Long id){
        return new ResponseEntity<>(noteService.updateNoteById(request, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteNote(@PathVariable Long id){
        return new ResponseEntity<>(noteService.deleteNoteById(id), HttpStatus.ACCEPTED);
    }
}