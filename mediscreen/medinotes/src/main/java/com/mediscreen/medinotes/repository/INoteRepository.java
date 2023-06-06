package com.mediscreen.medinotes.repository;

import com.mediscreen.medinotes.model.Note;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Repository
public interface INoteRepository extends MongoRepository<Note, Integer> {

    @Query("{name:'?0'}")
    Note findItemByName(String name);


    public long count();


    public List<Note> findAllNotesByIdPatient(int idPatient);


    public Optional<Note> findById(int id);
}
