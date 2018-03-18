package com.felipiberdun.order.repository;

import com.felipiberdun.order.domain.Cousine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public interface CousineRepository extends JpaRepository<Cousine, Long> {
}
