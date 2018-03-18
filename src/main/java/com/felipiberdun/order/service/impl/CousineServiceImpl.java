package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Cousine;
import com.felipiberdun.order.dto.external.StoreDto;
import com.felipiberdun.order.dto.mapper.StoreMapper;
import com.felipiberdun.order.repository.CousineRepository;
import com.felipiberdun.order.repository.StoreRepository;
import com.felipiberdun.order.service.CousineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class CousineServiceImpl implements CousineService {

    private final CousineRepository cousineRepository;
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Autowired
    public CousineServiceImpl(final CousineRepository cousineRepository,
                              final StoreRepository storeRepository,
                              final StoreMapper storeMapper) {
        this.cousineRepository = cousineRepository;
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    public List<Cousine> find() {
        return cousineRepository.findAll();
    }

    @Override
    public Optional<Cousine> findById(final Long id) {
        return Optional.ofNullable(cousineRepository.findOne(id));
    }

    @Override
    public List<StoreDto> findStoresByCousine(final Long cousineId) {
        return storeRepository.findByCousine(new Cousine(cousineId)).stream()
                .map(storeMapper::toDto)
                .collect(Collectors.toList());
    }

}
