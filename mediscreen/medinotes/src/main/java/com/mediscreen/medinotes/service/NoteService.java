package com.mediscreen.medinotes.service;


import com.mediscreen.medinotes.model.Note;
import com.mediscreen.medinotes.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private INoteRepository noteRepository;

    public List<Note> findAll() {return noteRepository.findAll();}

    public Optional<Note> findById(int id) { return noteRepository.findById(id); }

    public List<Note> listerLesNotesParPatient(int idPatient) {
          return noteRepository.findAllNotesByIdPatient(idPatient);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(int id) { noteRepository.deleteById(id); }
}
