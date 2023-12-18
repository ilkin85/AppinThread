package com.mia.xrs.service.Impl;

import com.mia.xrs.dto.RejectDto;
import com.mia.xrs.entity.Department;
import com.mia.xrs.entity.Letter;
import com.mia.xrs.entity.Reject;
import com.mia.xrs.entity.User;
import com.mia.xrs.mapper.impl.RejectMapper;
import com.mia.xrs.repository.RejectRepository;
import com.mia.xrs.service.RejectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectServiceImpl implements RejectService {

    private final RejectMapper rejectMapper;

    private final RejectRepository rejectRepository;


    @Override
    public Page<RejectDto> findAllPage(Integer pageSize, Integer pageNumber, String[] sortBy) {
        int defaultPageSize = 10;
        String[] defaultSortBy = {"rejectNo"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));

        return rejectRepository.findByStatus(true, pageable)
                .map(rejectMapper::toDto);
    }

    @Override
    public RejectDto findById(Integer id) {
        Reject reject = rejectRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new RuntimeException("Reject by id : " + id + " not found"));

        return rejectMapper.toDto(reject);
    }

    @Override
    @Transactional
    public RejectDto save(RejectDto rejectDto) {
        return null;
    }

    @Override
    @Transactional
    public RejectDto update(Integer id, RejectDto rejectDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Reject reject = rejectRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new RuntimeException("Reject by id : " + id + " not found"));

        reject.setStatus(false);
        rejectRepository.save(reject);

    }
}
