package com.migmig.repository;

import com.migmig.model.PostalCode;
import org.springframework.data.jpa.repository.Query;
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

    //TODO
    //needs optimization
    @Query("SELECT p FROM codigospostales p WHERE LOWER(p.colonia) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<PostalCode> findSimilarColonia(@Param("term") String term);

    //TODO
    //needs optimization
    @Query("SELECT p FROM codigospostales p WHERE LOWER(p.colonia) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(p.municipio) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(p.estado) LIKE LOWER(CONCAT('%', :term, '%')) OR p.codigoPostal LIKE LOWER(CONCAT('%', :term, '%')) order by p.estado, p.municipio, p.colonia")
    List<PostalCode> findSimilar(@Param("term") String term);
}
