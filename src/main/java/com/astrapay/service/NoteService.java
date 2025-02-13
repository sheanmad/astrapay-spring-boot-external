package com.astrapay.service;

import com.astrapay.dto.APIResponse;
import com.astrapay.dto.NoteDto;
import com.astrapay.dto.NoteRequestDto;
import com.astrapay.entity.Note;
import com.astrapay.exception.CustomException;
import com.astrapay.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;

    public APIResponse<List<NoteDto>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        List<NoteDto> notesResponse = notes.stream()
             .map(note -> modelMapper.map(note, NoteDto.class))
             .collect(Collectors.toList());
        return new APIResponse<>("success", Collections.singletonList("Notes fetched successfully"), notesResponse);
    }

    public APIResponse<NoteDto> getNoteById(Long id){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()){
            throw new CustomException("ID "+id+" is not found!", 404, "Status Failed");
        }
        Note noteFound = note.get();
        NoteDto noteResponse = modelMapper.map(noteFound, NoteDto.class);
        return new APIResponse<>("Success", Collections.singletonList("Note is found"), noteResponse);
    }

    public APIResponse<NoteDto> addNote(NoteRequestDto request){
        Optional<Note> checkTitle = noteRepository.findByTitle(request.getTitle());
        if(checkTitle.isPresent()){
            throw new CustomException("Note already exists!", 400, "Status Failed");
        }
        Note note = modelMapper.map(request, Note.class);
        noteRepository.save(note);
        NoteDto noteResponse = modelMapper.map(note, NoteDto.class);
        return new APIResponse<>("Success", Collections.singletonList("Note is added"), noteResponse);
    }

    public APIResponse<NoteDto> updateNoteById(NoteRequestDto request, Long id){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()){
            throw new CustomException("ID "+id+" is Not Found", 404, "Status Failed");
        }
        Note noteFound = note.get();
        noteFound.setTitle(request.getTitle());
        noteFound.setContent(request.getContent());
        noteRepository.save(noteFound);

        NoteDto response = modelMapper.map(noteFound, NoteDto.class);

        return new APIResponse<>("Success", Collections.singletonList("Note is updated!"), response);
    }

    public APIResponse<String> deleteNoteById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()){
            throw new CustomException("ID "+id+" is not Found", 404, "Status Failed");
        }
        noteRepository.deleteById(id);
        return new APIResponse<>("Success", Collections.singletonList("Note is deleted!"));
    }

}