package com.mia.xrs.service;

import com.mia.xrs.dto.RejectDto;
import org.springframework.data.domain.Page;

public interface RejectService {

    Page<RejectDto> findAllPage(Integer pageSize,
                                Integer pageNumber,
                                String[] sortBy);

    RejectDto findById(Integer id);
}
