package com.mia.xrs.mapper.impl;

import com.mia.xrs.dto.DepartmentDto;
import com.mia.xrs.dto.FormDto;
import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.PackageDto;
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
                                .note(letter.getNote())
                                .build())
                        .collect(Collectors.toList()))
                .sentDate(aPackage.getSentDate())
                .receiveDate(aPackage.getReceiveDate())
                .envelope(aPackage.getEnvelope())
                .parcel(aPackage.getParcel())
                .senderSignature(aPackage.getSenderSignature())
                .receiverSignature(aPackage.getReceiverSignature())
                .letterCount(aPackage.getLetterCount())
                .build();
    }

    @Override
    public Package toPackage(PackageDto packageDto) {
        return null;
    }
}
