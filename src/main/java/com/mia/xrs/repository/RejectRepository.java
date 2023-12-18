package com.mia.xrs.repository;

import com.mia.xrs.entity.Letter;
import com.mia.xrs.entity.Reject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RejectRepository extends JpaRepository<Reject,Integer> {

    @Query("SELECT COALESCE(MAX(l.uniqueId), 0) FROM Reject r")
    Integer findByMaxUniqueId();

    Page<Reject> findByStatus(Boolean status, Pageable pageable);

    Optional<Reject> findByIdAndStatus(Integer id, Boolean status);

    @Query("SELECT r FROM Reject r WHERE r.rejectNo = :rejectNo AND r.status = :status")
    Letter findByRejectNoAndStatus(@Param("rejectNo") Integer rejectNo, @Param("status") Boolean status);


}
