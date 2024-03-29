package edu.uh.tech.cis3368.semesterproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobStageRepository extends CrudRepository<JobStage, Integer> {
    JobStage findByOrdinal(int ordinal);
}
