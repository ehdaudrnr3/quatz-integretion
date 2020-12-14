package io.batch.schdul.batch.repository;

import io.batch.schdul.batch.domain.BatchJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchJobRepository extends JpaRepository<BatchJob, String> {

}
