package com.mia.xrs.mapper.impl;

import com.mia.xrs.dto.*;
import com.mia.xrs.entity.Package;
import com.mia.xrs.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PackageMapper implements Mapper<Package, PackageDto> {

    @Override
    public PackageDto toDto(Package aPackage) {

        return PackageDto.builder()
                .id(aPackage.getId())
                .form(FormDto.builder()
                        .id(aPackage.getForm().getId())
                        .formNumber(aPackage.getForm().getFormNumber())
                        .build())
                .packageNo(aPackage.getPackageNo())
                .letters(aPackage.getLetters().stream()
                        .map(letter -> LetterDto.builder()
                                .id(letter.getId())
                                .uniqueId(letter.getUniqueId())
                                .serialNo(letter.getSerialNo())
                                .letterNo(letter.getLetterNo())
                                .fromDepartment(DepartmentDto.builder()
                                        .id(letter.getFromDepartment().getId())
                                        .name(letter.getFromDepartment().getName())
                                        .parentId(letter.getFromDepartment().getParentId())
                                        .build())
                                .toDepartment(DepartmentDto.builder()
                                        .id(letter.getToDepartment().getId())
                                        .name(letter.getToDepartment().getName())
                                        .parentId(letter.getToDepartment().getParentId())
                                        .build())
                                .importanceDegree(letter.getImportanceDegree())
                                .envelope(letter.getEnvelope())
                                .parcel(letter.getParcel())
                                .createdBy(UserDto.builder()
                                        .id(letter.getCreatedBy().getId())
                                        .firstName(letter.getCreatedBy().getUserSpecification().getFirstName())
                                        .lastName(letter.getCreatedBy().getUserSpecification().getLastName())
                                        .fatherName(letter.getCreatedBy().getUserSpecification().getFatherName())
                                        .policeCard(letter.getCreatedBy().getUserSpecification().getPoliceCard())
                                        .rank(letter.getCreatedBy().getUserSpecification().getRank())
                                        .department(DepartmentDto.builder()
                                                .id(letter.getCreatedBy().getUserSpecification().getDepartment().getId())
                                                .name(letter.getCreatedBy().getUserSpecification().getDepartment().getName())
                                                .parentId(letter.getCreatedBy().getUserSpecification().getDepartment().getParentId())
                                                .build())
                                        .build())
                                .date(letter.getDate())
                                .note(letter.getNote())
                                .build())
                        .collect(Collectors.toList()))
                .sentDate(aPackage.getSentDate())
                .receiveDate(aPackage.getReceiveDate())
                .senderSignature(aPackage.getSenderSignature())
                .receiverSignature(aPackage.getReceiverSignature())
                .letterCount(aPackage.getLetterCount())
                .build();
    }

    @Override
    public Package toEntity(PackageDto packageDto) {
        return null;
    }
}