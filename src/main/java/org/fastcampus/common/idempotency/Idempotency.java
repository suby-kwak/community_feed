package org.fastcampus.common.idempotency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fastcampus.common.ui.Response;

@Getter
@AllArgsConstructor
public class Idempotency {
    private final String key;
    private final Response<?> response;
}
