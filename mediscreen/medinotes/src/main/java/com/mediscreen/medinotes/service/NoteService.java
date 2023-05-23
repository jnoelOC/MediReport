package com.mediscreen.medinotes.service;


import com.mediscreen.medinotes.model.Note;
import com.mediscreen.medinotes.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private INoteRepository noteRepository;

    public List<Note> findAll() {return noteRepository.findAll();}

}
