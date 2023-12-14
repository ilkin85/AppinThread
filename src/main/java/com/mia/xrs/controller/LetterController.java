package com.mia.xrs.controller;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.PackageDto;
import com.mia.xrs.entity.Package;
import com.mia.xrs.mapper.impl.PackageMapper;
import com.mia.xrs.repository.PackageRepository;
import com.mia.xrs.service.LetterService;
import com.mia.xrs.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;
    private final PackageService packageService;

    private final PackageMapper packageMapper;
    private final PackageRepository packageRepository;

    @GetMapping("/letters")
    public ResponseEntity<Page<LetterDto>> findAll(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                                   @RequestParam(name = "sortBy", required = false) String[] sortBy){

        return ResponseEntity.ok(letterService.findAllPage(pageSize, pageNumber, sortBy));
    }

    @GetMapping("/allFields/{contains}")
    public ResponseEntity<Page<LetterDto>> findAllFields(@PathVariable(value = "contains") String contains,
                                                         @RequestParam(name = "date", required = false) Date date,
                                                         @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                         @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                                         @RequestParam(name = "sortBy", required = false) String[] sortBy){

        return ResponseEntity.ok(letterService.findAllFields(date, contains, pageSize, pageNumber, sortBy));
    }

    @PostMapping("/letters")
    public ResponseEntity<LetterDto> save(@RequestBody LetterDto letterDto){

        return ResponseEntity.ok(letterService.save(letterDto));
    }

    @PostMapping("/package")
    public ResponseEntity<PackageDto> save(@RequestBody PackageDto packageDto){

        return ResponseEntity.ok(packageService.save(packageDto));
    }

    @PutMapping("/package/{id}")
    public ResponseEntity<PackageDto> update(@PathVariable Integer id, @RequestBody PackageDto packageDto){

        return ResponseEntity.ok(packageService.update(id, packageDto));
    }

    @PutMapping("/letters/{id}")
    public ResponseEntity<LetterDto> update(@PathVariable Integer id, @RequestBody LetterDto letterDto){
        return ResponseEntity.ok(letterService.update(id,letterDto));
    }


    @GetMapping("/sa/{id}")
    public PackageDto sal(@PathVariable Integer id){
        return packageMapper.toDto(packageRepository.findById(id).orElseThrow());
    }
}