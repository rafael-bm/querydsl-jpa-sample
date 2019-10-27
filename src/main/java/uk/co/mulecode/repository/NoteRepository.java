package uk.co.mulecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import uk.co.mulecode.domain.Note;

@Repository
public interface NoteRepository extends
        JpaRepository<Note, String>,
        QuerydslPredicateExecutor<Note> {

}
