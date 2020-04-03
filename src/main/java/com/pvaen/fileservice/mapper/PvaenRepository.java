package com.pvaen.fileservice.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvaen.fileservice.model.po.PvaenPO;

public interface PvaenRepository extends JpaRepository<PvaenPO, Long>{

}
