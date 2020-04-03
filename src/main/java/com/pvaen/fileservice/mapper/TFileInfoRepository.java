package com.pvaen.fileservice.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pvaen.fileservice.model.po.TFileInfo;

@Repository
public interface TFileInfoRepository extends JpaRepository<TFileInfo, Long>{
	
}
