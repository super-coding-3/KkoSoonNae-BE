package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}
