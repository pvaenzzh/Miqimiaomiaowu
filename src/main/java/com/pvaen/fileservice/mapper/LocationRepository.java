package com.pvaen.fileservice.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pvaen.fileservice.model.po.LocalPO;

@Repository
public interface LocationRepository extends JpaRepository<LocalPO, Long> {
	List<LocalPO> getLocationsByType(String type);
}
