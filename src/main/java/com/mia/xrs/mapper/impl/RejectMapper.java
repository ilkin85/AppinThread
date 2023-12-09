package com.mia.xrs.mapper.impl;

import com.mia.xrs.dto.DepartmentDto;
import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.RejectDto;
import com.mia.xrs.dto.UserDto;
import com.mia.xrs.entity.Reject;
import com.mia.xrs.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
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
                        .importanceDegree(reject.getLetter().getImportanceDegree())
                        .envelope(reject.getLetter().getEnvelope())
                        .parcel(reject.getLetter().getParcel())
                        .createdBy(UserDto.builder()
                                .id(reject.getLetter().getCreatedBy().getId())
                                .firstName(reject.getLetter().getCreatedBy().getUserSpecification().getFirstName())
                                .lastName(reject.getLetter().getCreatedBy().getUserSpecification().getLastName())
                                .fatherName(reject.getLetter().getCreatedBy().getUserSpecification().getFatherName())
                                .policeCard(reject.getLetter().getCreatedBy().getUserSpecification().getPoliceCard())
                                .rank(reject.getLetter().getCreatedBy().getUserSpecification().getRank())
                                .department(DepartmentDto.builder()
                                        .id(reject.getLetter().getCreatedBy().getUserSpecification().getDepartment().getId())
                                        .name(reject.getLetter().getCreatedBy().getUserSpecification().getDepartment().getName())
                                        .parentId(reject.getLetter().getCreatedBy().getUserSpecification().getDepartment().getParentId())
                                        .build())
                                .build())
                        .date(reject.getLetter().getDate())
                        .note(reject.getLetter().getNote())
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
    public Reject toEntity(RejectDto rejectDto) {
        return null;
    }
}