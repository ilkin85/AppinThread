package com.mia.xrs.repository;

import com.mia.xrs.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package,Integer> {
}
