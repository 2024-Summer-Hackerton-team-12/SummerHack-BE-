package team12.smartstudyplace.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team12.smartstudyplace.plan.Plan;
import team12.smartstudyplace.plan.value.Day;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
  List<Plan> findByDay(Day day);
  Optional<Plan> findById(Long id);
  List<Plan> findAll();
}

