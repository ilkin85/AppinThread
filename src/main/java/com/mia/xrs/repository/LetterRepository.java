package com.mia.xrs.repository;

import com.mia.xrs.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter,Integer> {
}
