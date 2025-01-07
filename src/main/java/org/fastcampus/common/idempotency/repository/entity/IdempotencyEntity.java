package org.fastcampus.common.idempotency.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.idempotency.Idempotency;
import org.fastcampus.common.utils.ResponseObjectMapper;

@Entity
@Table(name = "community_idempotency")
@AllArgsConstructor
@NoArgsConstructor
public class IdempotencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String idempotencyKey;

    @Getter
    @Column(nullable = false)
    private String response;

    public IdempotencyEntity(Idempotency idempotency) {
        this.idempotencyKey = idempotency.getKey();
        this.response = ResponseObjectMapper.toStringResponse(idempotency.getResponse());
    }

    public Idempotency toIdempotency() {
        return new Idempotency(this.idempotencyKey,
            ResponseObjectMapper.toResponseObject(response));
    }
}
