package com.mia.xrs.repository;

import com.mia.xrs.entity.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package,Integer> {

    @Query("SELECT COALESCE(MAX(p.uniqueId), 0) FROM Package p")
    Integer findByMaxUniqueId();

    Page<Package> findByStatus(Boolean status, Pageable pageable);

    Optional<Package> findByIdAndStatus(Integer id, Boolean status);
}
