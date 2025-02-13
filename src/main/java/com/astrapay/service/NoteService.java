package com.astrapay.service;

import com.astrapay.dto.APIResponse;
import com.astrapay.dto.NoteDto;
import com.astrapay.dto.NoteRequestDto;
import com.astrapay.entity.Note;
import com.astrapay.exception.CustomCheckedException;
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

    public static final String SUCCESS_STATUS = "Success";
    public static final String FAILED_STATUS = "Failed";
    public static final String NOTE_ADDED_MESSAGE = "Note is added!";
    public static final String NOTE_UPDATED_MESSAGE = "Note is updated!";
    public static final String NOTE_DELETED_MESSAGE = "Note is deleted!";
    public static final String NOTE_FOUND_MESSAGE = "Note is found!";
    public static final String NOTES_FETCHED_MESSAGE = "Successfully fetched all notes";
    public static final String NOTE_ALREADY_EXISTS_MESSAGE = "Note already exists!";
    public static final String ID_NOT_FOUND_MESSAGE = "ID %d is not found!";

    public APIResponse<List<NoteDto>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        List<NoteDto> notesResponse = notes.stream()
             .map(note -> modelMapper.map(note, NoteDto.class))
             .collect(Collectors.toList());
        return new APIResponse<>(SUCCESS_STATUS, Collections.singletonList(NOTES_FETCHED_MESSAGE), notesResponse);
    }

    public APIResponse<NoteDto> getNoteById(Long id){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()){
            throw new CustomCheckedException(NOTE_ALREADY_EXISTS_MESSAGE, 400, FAILED_STATUS);
        }
        Note noteFound = note.get();
        NoteDto noteResponse = modelMapper.map(noteFound, NoteDto.class);
        return new APIResponse<>(SUCCESS_STATUS, Collections.singletonList(NOTE_FOUND_MESSAGE), noteResponse);
    }

    public APIResponse<NoteDto> addNote(NoteRequestDto request){
        Optional<Note> checkTitle = noteRepository.findByTitle(request.getTitle());
        if(checkTitle.isPresent()){
            throw new CustomCheckedException(NOTE_ALREADY_EXISTS_MESSAGE, 400, FAILED_STATUS);
        }
        Note note = modelMapper.map(request, Note.class);
        noteRepository.save(note);
        NoteDto noteResponse = modelMapper.map(note, NoteDto.class);
        return new APIResponse<>(SUCCESS_STATUS, Collections.singletonList(NOTE_ADDED_MESSAGE), noteResponse);
    }

    public APIResponse<NoteDto> updateNoteById(NoteRequestDto request, Long id){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()){
            throw new CustomCheckedException(String.format(ID_NOT_FOUND_MESSAGE, id), 404, FAILED_STATUS);
        }
        Note noteFound = note.get();
        noteFound.setTitle(request.getTitle());
        noteFound.setContent(request.getContent());
        noteRepository.save(noteFound);

        NoteDto response = modelMapper.map(noteFound, NoteDto.class);
        return new APIResponse<>(SUCCESS_STATUS, Collections.singletonList(NOTE_UPDATED_MESSAGE), response);
    }

    public APIResponse<String> deleteNoteById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()){
            throw new CustomCheckedException(String.format(ID_NOT_FOUND_MESSAGE, id), 404, FAILED_STATUS);
        }
        noteRepository.deleteById(id);
        return new APIResponse<>(SUCCESS_STATUS, Collections.singletonList(NOTE_DELETED_MESSAGE));
    }

}