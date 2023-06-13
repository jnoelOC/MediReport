package com.mediscreen.medinotes.repository;

import com.mediscreen.medinotes.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INoteRepository extends MongoRepository<Note, String> {

    @Query("{name:'?0'}")
    Note findItemByName(String name);

    public long count();

/*
    @Query(value = "{}", sort = "{ id : -1 }", fields = "{ id : 1 }", limit = 1)
    public Integer findHighestId();
*/

    public List<Note> findAllNotesByIdPatient(int idPatient);


    public Optional<Note> findById(String id);
}
