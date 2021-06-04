package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface District repository.
 *
 * @author QuyetNK
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {}
