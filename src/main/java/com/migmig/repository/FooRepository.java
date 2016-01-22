package com.migmig.repository;

import com.migmig.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mramos on 1/22/2016.
 */

@Repository
public interface FooRepository extends JpaRepository<Foo, Long>{
}
