import com.astrapay.controller.NoteController;
import com.astrapay.dto.APIResponse;
import com.astrapay.dto.NoteDto;
import com.astrapay.dto.NoteRequestDto;
import com.astrapay.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

@WebMvcTest(NoteController.class)
@ContextConfiguration(classes = {NoteController.class, NoteService.class})
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddNote() throws Exception {
        NoteRequestDto requestDto = new NoteRequestDto("Test Note", "This is a test note");
        NoteDto noteDto = new NoteDto(1L, "Test Note", "This is a test note");
        APIResponse<NoteDto> apiResponse = new APIResponse<>("Success", Arrays.asList("Note is added!"), noteDto);

        when(noteService.addNote(any(NoteRequestDto.class))).thenReturn(apiResponse);

        mockMvc.perform(post("/v1/notes/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is("Success")))
                .andExpect(jsonPath("$.messages[0]", is("Note is added!")))
                .andExpect(jsonPath("$.data.title", is("Test Note")));
    }

    @Test
    void testGetAllNotes() throws Exception {
        NoteDto note1 = new NoteDto(1L, "Note 1", "Content 1");
        NoteDto note2 = new NoteDto(2L, "Note 2", "Content 2");
        List<NoteDto> noteList = Arrays.asList(note1, note2);
        APIResponse<List<NoteDto>> apiResponse = new APIResponse<>("Success", Arrays.asList("Successfully fetched all notes"), noteList);

        when(noteService.getAllNotes()).thenReturn(apiResponse);

        mockMvc.perform(get("/v1/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("Success")))
                .andExpect(jsonPath("$.messages[0]", is("Successfully fetched all notes")))
                .andExpect(jsonPath("$.data[0].title", is("Note 1")))
                .andExpect(jsonPath("$.data[1].title", is("Note 2")));
    }

    @Test
    void testGetNoteById() throws Exception {
        NoteDto note = new NoteDto(1L, "Test Note", "This is a test note");
        APIResponse<NoteDto> apiResponse = new APIResponse<>("Success", Arrays.asList("Note is found!"), note);

        when(noteService.getNoteById(1L)).thenReturn(apiResponse);

        mockMvc.perform(get("/v1/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("Success")))
                .andExpect(jsonPath("$.messages[0]", is("Note is found!")))
                .andExpect(jsonPath("$.data.title", is("Test Note")));
    }

    @Test
    void testUpdateNote() throws Exception {
        NoteRequestDto updatedNote = new NoteRequestDto("Updated Note", "Updated content");
        NoteDto noteDto = new NoteDto(1L, "Updated Note", "Updated content");
        APIResponse<NoteDto> apiResponse = new APIResponse<>("Success", Arrays.asList("Note is updated!"), noteDto);

        when(noteService.updateNoteById(any(NoteRequestDto.class), Mockito.eq(1L))).thenReturn(apiResponse);

        mockMvc.perform(put("/v1/notes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedNote)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("Success")))
                .andExpect(jsonPath("$.messages[0]", is("Note is updated!")));
    }

    @Test
    void testDeleteNote() throws Exception {
        APIResponse<String> apiResponse = new APIResponse<>("Success", Arrays.asList("Note is deleted!"));

        when(noteService.deleteNoteById(1L)).thenReturn(apiResponse);

        mockMvc.perform(delete("/v1/notes/1"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("Success")))
                .andExpect(jsonPath("$.messages[0]", is("Note is deleted!")));
    }
}