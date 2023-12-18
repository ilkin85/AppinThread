package com.mia.xrs.repository;

import com.mia.xrs.entity.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package,Integer> {

    @Query("SELECT COALESCE(MAX(p.uniqueId), 0) FROM Package p")
    Integer findByMaxUniqueId();

    Page<Package> findByStatus(Boolean status, Pageable pageable);

    Optional<Package> findByIdAndStatus(Integer id, Boolean status);

    @Query("SELECT COUNT(l) FROM Package p JOIN p.letters l WHERE p.packageNo = :packageNo AND p.status = :status")
    Integer countByLetterAndPackageStatus(@Param("packageNo") Integer packageNo, @Param("status") Boolean status);

    @Query("SELECT COUNT(l) FROM Package p JOIN p.letters l WHERE p.packageNo = :packageNo AND l.status = :status")
    Integer countByLetterAndStatus(@Param("packageNo") Integer packageNo, @Param("status") Boolean status);
}
