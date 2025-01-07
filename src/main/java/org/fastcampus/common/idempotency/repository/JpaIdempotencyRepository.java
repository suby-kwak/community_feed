package org.fastcampus.common.idempotency.repository;

import java.util.Optional;
import org.fastcampus.common.idempotency.repository.entity.IdempotencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIdempotencyRepository extends JpaRepository<IdempotencyEntity, Long> {

    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}
