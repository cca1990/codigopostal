package com.migmig.repository;

import com.migmig.model.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mramos on 1/22/2016.
 */
@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {
}
