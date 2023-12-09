package com.mia.xrs.repository;

import com.mia.xrs.entity.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter,Integer> {

    @Query("SELECT COALESCE(MAX(l.uniqueId), 0) FROM Letter l")
    Integer findByMaxUniqueId();

    @Query("SELECT COALESCE(MAX(l.serialNo), 0) FROM Letter l")
    Integer findBySerialNo();

    List<Letter> findByStatus(Boolean status, Sort sort);

    Page<Letter> findByStatus(Boolean status, Pageable pageable);

    Optional<Letter> findByIdAndStatus(Integer id, Boolean status);

    @Query("SELECT l FROM Letter l WHERE l.letterNo = :letterNo AND l.status = :status")
    Letter findByLetterNoAndStatus(@Param("letterNo") Integer letterNo, @Param("status") Boolean status);
//    @Query("SELECT l FROM Letter l WHERE l.date = :date AND l.status = :status")
//    Page<Letter> findByDateAndStatus(@Param("date") Timestamp date, @Param("status") Boolean status, Pageable pageable);
//
//    @Query("SELECT l FROM Letter l WHERE l.date = :date AND lower(l.fromDepartment) " +
//            "LIKE lower(concat('%', :fromDepartment, '%')) " + "AND l.status = :status")
//    Page<Letter> findByDateAndFromDepartmentAndStatus(@Param("date") Timestamp date, @Param("toDepartment") String fromDepartment,
//                                                      @Param("status") Boolean status, Pageable pageable);
//
//    @Query("SELECT l FROM Letter l WHERE l.date = :date AND lower(l.toDepartment) LIKE lower(concat('%', :toDepartment, '%')) AND l.status = :status")
//    Page<Letter> findByDateAndToDepartmentAndStatus(@Param("date") Timestamp date, @Param("toDepartment") String toDepartment,
//                                                    @Param("status") Boolean status, Pageable pageable);
//
//    @Query("SELECT l FROM Letter l WHERE l.date = :date AND (lower(l.fromDepartment) " +
//            "LIKE lower(concat('%', :fromDepartment, '%')) OR lower(l.toDepartment) " +
//            "LIKE lower(concat('%', :toDepartment, '%'))) AND l.status = :status")
//    Page<Letter> findByDateAndFromDepartmentOrToDepartmentAndStatus(@Param("date") Timestamp date, @Param("fromDepartment") String fromDepartment,
//                                                                    @Param("toDepartment") String toDepartment,
//                                                                    @Param("status") Boolean status, Pageable pageable);
//
//    @Query("SELECT l FROM Letter l WHERE lower(l.createdBy) LIKE lower(concat('%', :createdBy, '%')) AND l.status = :status")
//    Page<Letter> findByCreatedByAndStatus(@Param("createdBy") String createdBy, @Param("status") Boolean status, Pageable pageable);
//
//    @Query("SELECT l FROM Letter l WHERE l.date = :date AND lower(l.createdBy) LIKE lower(concat('%', :createdBy, '%')) AND l.status = :status")
//    Page<Letter> findByDateAndCreatedByAndStatus(@Param("date") Timestamp date, @Param("createdBy") String createdBy,
//                                                 @Param("status") Boolean status, Pageable pageable);
//
//    @Query("SELECT l FROM Letter l WHERE l.date = :date AND lower(l.createdBy) " +
//            "LIKE lower(concat('%', :createdBy, '%')) AND lower(l.fromDepartment) " +
//            "LIKE lower(concat('%', :fromDepartment, '%')) AND l.status = :status")
//    Page<Letter> findByDateAndCreatedByAndFromDepartmentAndStatus(@Param("date") Timestamp date, @Param("createdBy") String createdBy,
//                                                                  @Param("fromDepartment") String fromDepartment,
//                                                                  @Param("status") Boolean status, Pageable pageable);
//
//    @Query("SELECT l FROM Letter l WHERE l.date = :date AND lower(l.createdBy) " +
//            "LIKE lower(concat('%', :createdBy, '%')) AND lower(l.toDepartment) " +
//            "LIKE lower(concat('%', :toDepartment, '%')) AND l.status = :status")
//    Page<Letter> findByDateAndCreatedByAndToDepartmentAndStatus(@Param("date") Timestamp date, @Param("createdBy") String createdBy,
//                                                                @Param("fromDepartment") String toDepartment,
//                                                                @Param("status") Boolean status, Pageable pageable);
    @Query("SELECT l FROM Letter l WHERE l.date = :date OR lower(l.createdBy.userSpecification.firstName) " +
            "LIKE lower(concat('%', :contains, '%')) OR (lower(l.fromDepartment.name) " +
            "LIKE lower(concat('%', :contains, '%')) OR lower(l.toDepartment.name) " +
            "LIKE lower(concat('%', :contains, '%'))) AND l.status = :status")
    Page<Letter> findByDateAndCreatedByAndFromDepartmentOrToDepartmentAndStatus(Date date,
                                                                                String contains,
                                                                                Boolean status,
                                                                                Pageable pageable);

}