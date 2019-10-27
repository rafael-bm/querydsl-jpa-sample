package uk.co.mulecode.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.Instant;
import java.util.UUID;

@Data
@QueryEntity
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Note {

    @Id
    private String id;
    private String title;
    private String note;
    private Instant createdAt;
    private Instant lastUpdatedAt;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
    }
}
