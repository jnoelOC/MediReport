package com.mediscreen.medinotes.service;


import com.mediscreen.medinotes.model.Note;
import com.mediscreen.medinotes.repository.INoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    private INoteRepository noteRepository;

    public List<Note> findAll() {return noteRepository.findAll();}

    public Optional<Note> findById(String id) { return noteRepository.findById(id); }

    public List<Note> listerLesNotesParPatient(Integer idPatient) {
          return noteRepository.findAllNotesByIdPatient(idPatient);
    }

    public Note save(Note note) {
        note.setId(UUID.randomUUID().toString().split("-")[0]);
        return noteRepository.save(note);
    }

    public Note update(Note noteRequest) {

        Note existingNote = noteRepository.findById(noteRequest.getId()).get();

        existingNote.setName(noteRequest.getName());
        existingNote.setNote(noteRequest.getNote());
        existingNote.setIdPatient(noteRequest.getIdPatient());

        return noteRepository.save(existingNote);
    }

    public void deleteById(String id) { noteRepository.deleteById(id); }


}
