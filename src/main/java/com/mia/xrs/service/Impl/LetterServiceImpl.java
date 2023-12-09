package com.mia.xrs.service.Impl;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.entity.Department;
import com.mia.xrs.entity.Letter;
import com.mia.xrs.mapper.impl.LetterMapper;
import com.mia.xrs.repository.DepartmentRepository;
import com.mia.xrs.repository.LetterRepository;
import com.mia.xrs.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class LetterServiceImpl implements LetterService {

    private final LetterRepository letterRepository;
    private final DepartmentRepository departmentRepository;
    private final LetterMapper letterMapper;

    @Override
    public Page<LetterDto> findAllPage(Integer pageSize, Integer pageNumber, String[] sortBy) {

        Integer defaultPageSize = 10;
        String[] defaultSortBy = {"letterNo"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));

        return letterRepository.findByStatus(true, pageable)
                .map(letterMapper::toDto);
    }

    @Override
    public LetterDto findById(Integer id) {

        Letter letter = letterRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new RuntimeException("Letter by id : " + id + " not found"));

        return letterMapper.toDto(letter);
    }

    @Override
    public LetterDto findByLetterNo(Integer letterNo) {

        Letter letter = letterRepository.findByLetterNoAndStatus(letterNo, true);

        return letterMapper.toDto(letter);
    }

    @Override
    public Page<LetterDto> findAllFields(Date date,
                                         String contains,
                                         Integer pageSize,
                                         Integer pageNumber,
                                         String[] sortBy) {

        Integer defaultPageSize = 10;
        String[] defaultSortBy = {"letterNo"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sortBy));

        return letterRepository.findByDateAndCreatedByAndFromDepartmentOrToDepartmentAndStatus(date, contains, true, pageable)
                .map(letterMapper::toDto);
    }

    @Override
    @Transactional
    public LetterDto save(LetterDto letterDto) {

        Letter letter = new Letter();
        letter.setId(null);
        letter.setStatus(true);
        letter.setUniqueId(letterRepository.findByMaxUniqueId() + 1);
        letter.setSerialNo(letterRepository.findBySerialNo() + 1);
        letter.setLetterNo(letterDto.getLetterNo());
        letter.setCreatedAt(null);

        Department fromDepartment = departmentRepository.findById(letterDto.getFromDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department by ID: " + letterDto.getFromDepartment().getId() + " not found"));

        Department toDepartment = departmentRepository.findById(letterDto.getToDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department by ID: " + letterDto.getToDepartment().getId() + " not found"));

        letter.setFromDepartment(fromDepartment);
        letter.setToDepartment(toDepartment);


        letter.setImportanceDegree(letterDto.getImportanceDegree());

        if (letterDto.getEnvelope() == null){
            letterDto.setEnvelope(1);
        }

        letter.setEnvelope(letterDto.getEnvelope());
        letter.setParcel(letterDto.getParcel());
        letter.setDate(letterDto.getDate());
        letter.setNote(letterDto.getNote());

        Letter save = letterRepository.save(letter);

        return letterMapper.toDto(save);
    }

    @Override
    @Transactional
    public LetterDto update(Integer id,LetterDto letterDto) {

        Letter letter = letterRepository.findByIdAndStatus(id,true)
                .orElseThrow(() -> new RuntimeException("Letter by id : " + id + " not found"));

        letter.setStatus(false);

        Letter newLetter = new Letter();
        newLetter.setId(null);
        newLetter.setUniqueId(letter.getUniqueId());
        newLetter.setStatus(true);
        newLetter.setCreatedAt(null);
        newLetter.setLetterNo(letterDto.getLetterNo());
        newLetter.setDate(letterDto.getDate());

        if (letterDto.getEnvelope() == null){
            letterDto.setEnvelope(1);
        }

        newLetter.setEnvelope(letterDto.getEnvelope());
        newLetter.setParcel(letterDto.getParcel());
        newLetter.setImportanceDegree(letterDto.getImportanceDegree());
        newLetter.setNote(letterDto.getNote());

        Department fromDepartment = departmentRepository.findById(letterDto.getFromDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department by id : " + id + " not found"));

        newLetter.setFromDepartment(fromDepartment);

        Department toDepartment = departmentRepository.findById(letterDto.getToDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department by id : " + id + " not found"));

        newLetter.setToDepartment(toDepartment);
        newLetter.setCreatedBy(letter.getCreatedBy());

        Letter save = letterRepository.save(newLetter);

        return letterMapper.toDto(save);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        Letter letter = letterRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new RuntimeException("Letter by id : " + id + " not found"));

        letter.setStatus(false);
        letterRepository.save(letter);
    }
}
