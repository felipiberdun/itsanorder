package com.felipiberdun.order.service.impl;

import com.felipiberdun.order.domain.Cousine;
import com.felipiberdun.order.dto.output.CousineDto;
import com.felipiberdun.order.dto.output.StoreDto;
import com.felipiberdun.order.dto.mapper.CousineMapper;
import com.felipiberdun.order.dto.mapper.StoreMapper;
import com.felipiberdun.order.exception.EntityNotFoundException;
import com.felipiberdun.order.repository.CousineRepository;
import com.felipiberdun.order.repository.StoreRepository;
import com.felipiberdun.order.service.CousineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@Service
public class CousineServiceImpl implements CousineService {

    private final CousineRepository cousineRepository;
    private final StoreRepository storeRepository;
    private final CousineMapper cousineMapper;
    private final StoreMapper storeMapper;

    @Autowired
    public CousineServiceImpl(final CousineRepository cousineRepository,
                              final StoreRepository storeRepository,
                              final CousineMapper cousineMapper,
                              final StoreMapper storeMapper) {
        this.cousineRepository = cousineRepository;
        this.storeRepository = storeRepository;
        this.cousineMapper = cousineMapper;
        this.storeMapper = storeMapper;
    }

    @Override
    public List<CousineDto> find() {
        return cousineRepository.findAll().stream()
                .map(cousineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CousineDto findById(final Long id) {
        return cousineRepository.findById(id)
                .map(cousineMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<StoreDto> findStoresByCousine(final Long cousineId) {
        return storeRepository.findByCousine(new Cousine(cousineId)).stream()
                .map(storeMapper::toDto)
                .collect(Collectors.toList());
    }

}
