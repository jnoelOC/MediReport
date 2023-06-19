package com.mediscreen.medinotes.ut.controller;

import com.mediscreen.medinotes.model.Note;
import com.mediscreen.medinotes.service.NoteService;
import com.mediscreen.medinotes.web.controller.NoteController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class NoteControllerTest {

    @InjectMocks
    private NoteController noteController;

    @Mock
    private NoteService noteService;

    @BeforeEach
    public void setup() { }


    @Test
    @DisplayName("findAllNotes : OK")
    void whenValidInputFindAll_thenReturnsListOfNotes() {
        // ARRANGE
        List<Note> notes = Arrays.asList(
                new Note("4d5128fe", "name11", "note11", 3),
                new Note("1e5628ab", "name33", "note33", 3),
                new Note("3034e120", "name22", "note22", 3));

        when(noteService.findAll()).thenReturn(notes);

        // ACT
        List<Note> result = noteController.listerToutesLesNotes();

        // ASSERT
        Assertions.assertEquals(notes.size(), result.size());
    }

    @Test
    @DisplayName("findAllNotes : EMPTY")
    void whenInvalidInputFindAll_thenReturnsEmpty() {
        // ARRANGE
        when(noteService.findAll()).thenReturn(Collections.emptyList());

        // ACT
        List<Note> result = noteController.listerToutesLesNotes();

        // ASSERT
        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("findAllNotes by patient : OK")
    void whenValidInputFindAllNotesByPatient_thenReturnsListOfNotes() {
        // ARRANGE
        List<Note> notes = Arrays.asList(
                new Note("4d5128fe", "name11", "note11", 3),
                new Note("1e5628ab", "name33", "note33", 3),
                new Note("3034e120", "name22", "note22", 3));

        when(noteService.listerLesNotesParPatient(anyInt())).thenReturn(notes);

        // ACT
        List<Note> result = noteController.listerToutesLesNotesParPatient(1);

        // ASSERT
        Assertions.assertEquals(notes.size(), result.size());
    }

    @Test
    @DisplayName("findAllNotes by patient : EMPTY")
    void whenInvalidInputFindAllNotesByPatient_thenReturnsEmpty() {
        // ARRANGE
        when(noteService.listerLesNotesParPatient(anyInt())).thenReturn(Collections.emptyList());

        // ACT
        List<Note> result = noteController.listerToutesLesNotesParPatient(2);

        // ASSERT
        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("Add a note : CREATED")
    void whenValidInputAddnote_thenReturnsCreated() {
        // ARRANGE
        Note note = new Note("4d5128fe", "name11", "note11", 3);

        when(noteService.save(any(Note.class))).thenReturn(note);

        // ACT
        ResponseEntity<Note> result = noteController.ajouterUneNote(note);

        // ASSERT
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    @DisplayName("Add a note : NO_CONTENT")
    void whenInvalidInputAddnote_thenReturnsNoContent() {
        // ARRANGE
        Note note = new Note("4d5128fe", "name11", "note11", 3);

        when(noteService.save(any(Note.class))).thenReturn(null);

        // ACT
        ResponseEntity<Note> result = noteController.ajouterUneNote(note);

        // ASSERT
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    @DisplayName("Update a note GET : OK")
    void whenValidInputUpdateNoteGet_thenReturnsOk() {
        // ARRANGE
        Note note = new Note("4d5128fe", "name11", "note11", 3);
        when(noteService.findById(any(String.class))).thenReturn(Optional.of(note));

        // ACT
        ResponseEntity<Note> result = noteController.modifierUneNoteGet("4d5128fe");

        // ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("Update a note GET : NOT_FOUND")
    void whenInvalidInputUpdateNoteGet_thenReturnsNotFound() {
        // ARRANGE
        Note note = null;
        when(noteService.findById(any(String.class))).thenReturn(Optional.ofNullable(note));

        // ACT
        ResponseEntity<Note> result = noteController.modifierUneNoteGet("4d5128fe");

        // ASSERT
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }



    @Test
    @DisplayName("Update a note POST : OK")
    void whenValidInputUpdateNotePost_thenReturnsOk() {
        // ARRANGE
        Note noteToBeModified = new Note("4d5128fe", "name77", "note77", 3);
        Note noteModified = new Note("4d5128fe", "name11", "note11", 3);
        when(noteService.update(any(Note.class))).thenReturn(noteModified);

        // ACT
        ResponseEntity<Note> result = noteController.modifierUneNote(noteToBeModified);

        // ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("Update a note POST : NO_CONTENT")
    void whenInvalidInputUpdateNotePost_thenReturnsNoContent() {
        // ARRANGE
        Note noteToBeModified = new Note("4d5128fe", "name77", "note77", 3);
        when(noteService.update(any(Note.class))).thenReturn(null);

        // ACT
        ResponseEntity<Note> result = noteController.modifierUneNote(noteToBeModified);

        // ASSERT
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    @DisplayName("Delete a note : OK")
    void whenValidInputDeleteNoteById_thenReturnsOk() {
        // ARRANGE
        willDoNothing().given(noteService).deleteById(anyString());
        // ACT
        ResponseEntity<HttpStatus> result = noteController.effacerUneNote("4d5128fe");

        // ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(noteService, times(1)).deleteById(anyString());
    }

    @Test
    @DisplayName("Delete a note : NOT_FOUND")
    void whenInvalidInputDeleteNoteById_thenReturnsNotFound() {
        // ARRANGE
        willDoNothing().given(noteService).deleteById(anyString());
        // ACT
        ResponseEntity<HttpStatus> result = noteController.effacerUneNote(null);

        // ASSERT
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(noteService, times(0)).deleteById(anyString());
    }

}
