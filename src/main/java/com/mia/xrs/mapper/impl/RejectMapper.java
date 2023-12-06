package com.mia.xrs.mapper.impl;

import com.mia.xrs.dto.DepartmentDto;
import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.RejectDto;
import com.mia.xrs.dto.UserDto;
import com.mia.xrs.entity.Reject;
import com.mia.xrs.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class RejectMapper implements Mapper<Reject, RejectDto> {

    @Override
    public RejectDto toDto(Reject reject) {
        return RejectDto.builder()
                .id(reject.getId())
                .routeNo(reject.getRouteNo())
                .returnDate(reject.getReturnDate())
                .letter(LetterDto.builder()
                        .id(reject.getLetter().getId())
                        .letterNo(reject.getLetter().getLetterNo())
                        .fromDepartment(DepartmentDto.builder()
                                .id(reject.getLetter().getFromDepartment().getId())
                                .name(reject.getLetter().getFromDepartment().getName())
                                .parentId(reject.getLetter().getFromDepartment().getParentId())
                                .build())
                        .toDepartment(DepartmentDto.builder()
                                .id(reject.getLetter().getToDepartment().getId())
                                .name(reject.getLetter().getToDepartment().getName())
                                .parentId(reject.getLetter().getToDepartment().getParentId())
                                .build())
                        .build())
                .rejectReason(reject.getRejectReason())
                .returner(UserDto.builder()
                        .firstName(reject.getReturner().getUserSpecification().getFirstName())
                        .lastName(reject.getReturner().getUserSpecification().getLastName())
                        .fatherName(reject.getReturner().getUserSpecification().getLastName())
                        .rank(reject.getReturner().getUserSpecification().getRank())
                        .build())
                .receiver(UserDto.builder()
                        .firstName(reject.getReceiver().getUserSpecification().getFirstName())
                        .lastName(reject.getReceiver().getUserSpecification().getLastName())
                        .fatherName(reject.getReceiver().getUserSpecification().getLastName())
                        .rank(reject.getReceiver().getUserSpecification().getRank())
                        .build())
                .returnerSignature(reject.getReturnerSignature())
                .receiverSignature(reject.getReceiverSignature())
                .build();
    }

    @Override
    public Reject toPackage(RejectDto rejectDto) {
        return null;
    }
}
