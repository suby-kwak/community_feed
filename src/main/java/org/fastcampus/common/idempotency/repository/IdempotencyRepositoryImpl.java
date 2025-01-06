package org.fastcampus.common.idempotency.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.common.idempotency.Idempotency;
import org.fastcampus.common.idempotency.IdempotencyRepository;
import org.fastcampus.common.idempotency.repository.entity.IdempotencyEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IdempotencyRepositoryImpl implements IdempotencyRepository {

    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        Optional<IdempotencyEntity> idempotencyEntity = jpaIdempotencyRepository.findByIdempotencyKey(key);
        return idempotencyEntity.map(IdempotencyEntity::toIdempotency).orElse(null);
    }

    @Override
    @Transactional
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdempotencyEntity(idempotency));
    }
}
