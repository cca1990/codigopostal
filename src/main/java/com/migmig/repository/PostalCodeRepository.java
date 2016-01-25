package com.migmig.repository;

import com.migmig.model.PostalCode;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mramos on 1/22/2016.
 */
@Repository
public interface PostalCodeRepository extends PagingAndSortingRepository<PostalCode, Long> {
    List<PostalCode> findByCodigoPostal(@Param("cp") long codigoPostal);
    List<PostalCode> findByColonia(@Param("col") String colonia);
}
