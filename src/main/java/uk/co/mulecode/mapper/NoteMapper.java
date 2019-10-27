package uk.co.mulecode.mapper;

import uk.co.mulecode.domain.Note;

import java.time.Instant;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public interface NoteMapper {

    UnaryOperator<Note> createNote = s -> {
        s.setCreatedAt(Instant.now());
        s.setLastUpdatedAt(Instant.now());
        return s;
    };

    BiFunction<Note, Note, Note> patchNote = (s, v) -> {
        s.setTitle(v.getTitle());
        s.setNote(v.getNote());
        s.setLastUpdatedAt(Instant.now());
        return s;
    };
}
