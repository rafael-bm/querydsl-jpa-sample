package uk.co.mulecode.resource;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uk.co.mulecode.domain.Note;
import uk.co.mulecode.mapper.NoteMapper;
import uk.co.mulecode.repository.NoteRepository;

import static java.util.Optional.of;

@Slf4j
@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteResource implements ResourceHttpFunc, NoteMapper {

    private final NoteRepository noteRepository;

    @GetMapping({"", "/"})
    public Page<Note> findAll(@QuerydslPredicate(root = Note.class) Predicate predicate,
                              @PageableDefault Pageable page) {
        log.info("findAll - query: {}", predicate);

        return noteRepository.findAll(predicate, page);
    }

    @PostMapping({"", "/"})
    public Note create(@RequestBody Note note) {

        return of(note)
                .map(createNote)
                .map(noteRepository::save)
                .orElseThrow(internalErrorSupplierResponse);
    }

    @GetMapping("/{id}")
    public Note findOne(@PathVariable("id") String id) {

        return noteRepository.findById(id)
                .orElseThrow(notFoundResponse.apply(id));
    }

    @PutMapping("/{id}")
    public Note modify(@PathVariable("id") String id,
                       @RequestBody Note notePatch) {

        return noteRepository.findById(id)
                .map(note -> patchNote.apply(note, notePatch))
                .map(noteRepository::save)
                .orElseThrow(notFoundResponse.apply(id));
    }

}
