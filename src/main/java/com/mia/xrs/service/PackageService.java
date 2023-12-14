package com.mia.xrs.service;

import com.mia.xrs.dto.PackageDto;

public interface PackageService {

    PackageDto save(PackageDto packageDto);

    PackageDto update(Integer id,PackageDto packageDto);
}
