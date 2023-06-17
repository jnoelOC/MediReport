package com.mediscreen.medinotes.ut.service;

import com.mediscreen.medinotes.model.Note;
import com.mediscreen.medinotes.repository.INoteRepository;
import com.mediscreen.medinotes.service.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class NoteServiceTest {

    @InjectMocks
    private NoteService noteService;

    @Mock
    private INoteRepository noteRepository;


    @BeforeEach
    public void setup() { }



    @Test
    @DisplayName("Find all notes : OK")
    void whenValidFindAll_thenReturnsListOfNotes() {
        // ARRANGE
        List<Note> notes = Arrays.asList(
            new Note("4d5128fe", "name11", "note11", 3),
            new Note("1e5628ab", "name33", "note33", 3),
            new Note("3034e120", "name22", "note22", 3));
        when(noteRepository.findAll()).thenReturn(notes);

        // ACT
        List<Note> listOfNotes = noteService.findAll();

        // ASSERT
        Assertions.assertNotNull(listOfNotes);
        Assertions.assertEquals(notes.size(), listOfNotes.size());
    }

    @Test
    @DisplayName("Find all notes : EMPTY")
    void whenValidFindAll_thenReturnsListEmpty() {
        // ARRANGE
        List<Note> notes = List.of();
        when(noteRepository.findAll()).thenReturn(notes);

        // ACT
        List<Note> listOfNotes = noteService.findAll();

        // ASSERT
        Assertions.assertEquals(Collections.emptyList(), listOfNotes);
    }

    @Test
    @DisplayName("Find note by id : OK")
    void whenValidInputFindById_thenReturnsOneNote() {
        // ARRANGE
        Note note = new Note("4d5128fe", "name11", "note11", 3);
        when(noteRepository.findById(any(String.class))).thenReturn(Optional.of(note));

        // ACT
        Optional<Note> note1 = noteService.findById("12345678");

        // ASSERT
        if(note1.isPresent()) {
            Assertions.assertNotNull(note1.get());
            Assertions.assertEquals(note1.get(), note);
        }
    }

    @Test
    @DisplayName("Find note by id : EMPTY")
    void whenInvalidInputFindById_thenReturnsEmpty() {
        // ARRANGE
        when(noteRepository.findById(any(String.class))).thenReturn(Optional.empty());

        // ACT
        Optional<Note> note1 = noteService.findById("12345678");

        // ASSERT
        if(note1.isEmpty()) {
            Assertions.assertEquals(note1, Optional.empty());
        }
    }

    @Test
    @DisplayName("Find all notes by IdPatient : OK")
    void whenValidFindAllNotesByPatient_thenReturnsListOfNotes() {
        // ARRANGE
        List<Note> notes = Arrays.asList(
                new Note("4d5128fe", "name11", "note11", 3),
                new Note("3034e120", "name22", "note22", 3));
        when(noteRepository.findAllNotesByIdPatient(any(Integer.class))).thenReturn(notes);

        // ACT
        List<Note> listOfNotes = noteService.listerLesNotesParPatient(2);

        // ASSERT
        Assertions.assertNotNull(listOfNotes);
        Assertions.assertEquals(notes.size(), listOfNotes.size());
    }


    @Test
    @DisplayName("Find all notes by IdPatient : NULL")
    void whenInvalidFindAllNotesByPatient_thenReturnsNull() {
        // ARRANGE
        when(noteRepository.findAllNotesByIdPatient(any(Integer.class))).thenReturn(null);

        // ACT
        List<Note> listOfNotes = noteService.listerLesNotesParPatient(1);

        // ASSERT
        Assertions.assertNull(listOfNotes);
    }

    @Test
    @DisplayName("Save one note : OK")
    void whenValidInputSaveNote_thenReturnsOneNote() {
        // ARRANGE
        Note note = new Note("87654321", "name11", "note11", 3);
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        // ACT
        Optional<Note> note1 = Optional.ofNullable(noteService.save(note));

        // ASSERT
        if(note1.isPresent()) {
            Assertions.assertNotNull(note1.get());
            Assertions.assertNotNull(note1.get().getId());
            Assertions.assertEquals(note1.get(), note);
        }
    }

    @Test
    @DisplayName("Save one note : NULL")
    void whenInvalidInputSaveNote_thenReturnsNull() {
        // ARRANGE
        Note note = new Note("87654321", "name11", "note11", 3);
        when(noteRepository.save(any(Note.class))).thenReturn(null);

        // ACT
        Optional<Note> note1 = Optional.ofNullable(noteService.save(note));

        // ASSERT
        if(note1.isEmpty()) {
            Assertions.assertEquals(note1, Optional.empty());
        }
    }

    @Test
    @DisplayName("Update one note : OK")
    void whenValidInputUpdateNote_thenReturnsOneModifiedNote() {
        // ARRANGE
        Note note1 = new Note("87654321", "name11", "note11", 3);
        Note note2 = new Note("87654321", "name22", "note22", 3);
        when(noteRepository.findById("87654321")).thenReturn(Optional.ofNullable(note1));

        when(noteRepository.save(any(Note.class))).thenReturn(note2);

        // ACT
        Optional<Note> note3 = Optional.ofNullable(noteService.update(note1));

        // ASSERT
        if(note3.isPresent()) {
            Assertions.assertNotNull(note3.get());
            Assertions.assertEquals(note3.get().getId(), note2.getId());
            Assertions.assertEquals(note3.get(), note2);
        }
    }


    @Test
    @DisplayName("Delete one note by id : OK")
    void whenValidInputDeleteNote_thenReturnsVoid() {
        // ARRANGE
        willDoNothing().given(noteRepository).deleteById(anyString());

        // ACT
        noteService.deleteById("87654321");

        // ASSERT
        verify(noteRepository, times(1)).deleteById("87654321");
    }
}
