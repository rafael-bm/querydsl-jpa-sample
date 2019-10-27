# Querydsl JPA sample
Spring boot application with spring data and MCV with QueryDSL

On this sample project shows how to enable QueryDSL on MVC and JPA

MVC

```java
    @GetMapping({"", "/"})
    public Page<Note> findAll(@QuerydslPredicate(root = Note.class) Predicate predicate,
                              @PageableDefault Pageable page) {
        log.info("findAll - query: {}", predicate);

        return noteRepository.findAll(predicate, page);
    }
```

JPA

```java
@Repository
public interface NoteRepository extends
        JpaRepository<Note, String>,
        QuerydslPredicateExecutor<Note> {

}
```

Entity

```java
@QueryEntity
@Entity
public class Note { }
```

Depedencies:


```
annotationProcessor("com.querydsl:querydsl-apt:4.2.1:jpa")
implementation("com.querydsl:querydsl-core:4.2.1")
implementation("com.querydsl:querydsl-jpa:4.2.1")
```
