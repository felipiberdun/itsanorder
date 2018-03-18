package com.felipiberdun.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends BaseException {
}
