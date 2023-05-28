package model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class AuditedEntity extends BaseEntity {
    @Column(name = "CreationTime", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationTime;

    @Column(name = "LastModificationTime")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastModificationTime;

    @Column(name = "IsDeleted", columnDefinition = "bit default 0", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @PrePersist
    public void defaultValue() {
        if (Objects.isNull(isDeleted)) {
            isDeleted = false;
        }
        if (Objects.isNull(creationTime)) {
            creationTime = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        }
    }
}
