package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Street repository.
 *
 * @author QuyetNK
 */
@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {

}
