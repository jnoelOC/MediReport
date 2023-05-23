package com.mediscreen.medinotes.repository;

import com.mediscreen.medinotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteRepository extends MongoRepository<Note, String> {

    @Query("{name:'?0'}")
    Note findItemByName(String name);

    public long count();
}
