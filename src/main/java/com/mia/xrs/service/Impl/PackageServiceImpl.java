package com.mia.xrs.service.Impl;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.PackageDto;
import com.mia.xrs.entity.Department;
import com.mia.xrs.entity.Letter;
import com.mia.xrs.entity.Package;
import com.mia.xrs.mapper.impl.LetterMapper;
import com.mia.xrs.mapper.impl.PackageMapper;
import com.mia.xrs.repository.DepartmentRepository;
import com.mia.xrs.repository.FormRepository;
import com.mia.xrs.repository.LetterRepository;
import com.mia.xrs.repository.PackageRepository;
import com.mia.xrs.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final LetterRepository letterRepository;
    private final PackageRepository packageRepository;
    private final DepartmentRepository departmentRepository;
    private final FormRepository formRepository;
    private final PackageMapper packageMapper;


    @Override
    @Transactional
    public PackageDto save(PackageDto packageDto) {

        Package aPackage = new Package();
        aPackage.setId(null);
        aPackage.setStatus(true);
        aPackage.setUniqueId(packageRepository.findByMaxUniqueId() + 1);
        aPackage.setPackageNo(packageDto.getPackageNo());
        aPackage.setCreatedAt(null);
        aPackage.setSentDate(packageDto.getSentDate());
        aPackage.setReceiveDate(packageDto.getReceiveDate());
        aPackage.setReceiverSignature(packageDto.getReceiverSignature());
        aPackage.setSenderSignature(packageDto.getSenderSignature());

        aPackage.setForm(formRepository.findById(packageDto.getForm().getId())
                .orElseThrow());

        List<Letter> letters = new ArrayList<>();

        Letter letter;
        for (LetterDto letterDto : packageDto.getLetters()){
            letter = letterRepository.findByIdAndStatus(letterDto.getId(), true)
                    .orElseThrow();
            letters.add(letter);
        }

        aPackage.setLetters(letters);
        aPackage.setLetterCount(aPackage.getLetters().size());
////        Letter letter = new Letter();
//        letter.setSerialNo(0);

//            for (LetterDto letterDto : packageDto.getLetters()) {
//                letter.setId(null);
//                letter.setStatus(true);
//                letter.setAPackage(aPackage);
//                letter.setFromDepartment(departmentRepository.findById(letterDto.getFromDepartment().getId())
//                        .orElseThrow(()-> new RuntimeException("Letter by id : " + letterDto.getFromDepartment().getId()+ " not found")));
//                letter.setToDepartment(departmentRepository.findById(letterDto.getToDepartment().getId())
//                        .orElseThrow(()-> new RuntimeException("Letter by id : " + letterDto.getToDepartment().getId()+ " not found")));
//                letter.setLetterNo(letterDto.getLetterNo());
//                letter.setParcel(letterDto.getParcel());
//                letter.setDate(letterDto.getDate());
//                letter.setNote(letterDto.getNote());
//                letter.setEnvelope(letterDto.getEnvelope());
//                letter.setUniqueId(letterRepository.findByMaxUniqueId() + 1);
//                letter.setSerialNo(letter.getSerialNo() + 1);
//                letter.setImportanceDegree(letterDto.getImportanceDegree());
//                letters.add(letter);
//            }

//        letterRepository.save(letter);
        Package save = packageRepository.save(aPackage);

        return packageMapper.toDto(save);

    }
}
