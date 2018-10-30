package com.johnhunsley.hateoas.wizard.repository;

import com.johnhunsley.hateoas.wizard.model.AbstractStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("stepRepository")
public interface StepRepositoryJpaImpl extends JpaRepository<AbstractStep, Integer> {


}
