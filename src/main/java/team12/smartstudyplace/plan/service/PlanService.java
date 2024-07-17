package team12.smartstudyplace.plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team12.smartstudyplace.plan.Plan;
import team12.smartstudyplace.plan.presentation.dto.Description;
import team12.smartstudyplace.plan.presentation.dto.PlanDescriptions;
import team12.smartstudyplace.plan.presentation.dto.PlanRequest;
import team12.smartstudyplace.plan.repository.PlanRepository;
import team12.smartstudyplace.plan.value.Day;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanService {

  private final PlanRepository planRepository;

  public void save(PlanRequest planRequest) {
    for (Map.Entry<Day, PlanDescriptions> entry : planRequest.plans().entrySet()) {
      Day day = entry.getKey();
      PlanDescriptions todoPlan = entry.getValue();
      if (todoPlan.description() != null) {
        updatePlanDescription(todoPlan.description().id(), day, todoPlan.description().time(),todoPlan.description().text());
      }
      if (todoPlan.description2() != null) {
        updatePlanDescription(todoPlan.description2().id(), day, todoPlan.description2().time(), todoPlan.description2().text());
      }
      if (todoPlan.description3() != null) {
        updatePlanDescription(todoPlan.description3().id(), day, todoPlan.description3().time(), todoPlan.description3().text());
      }
      if (todoPlan.description4() != null) {
        updatePlanDescription(todoPlan.description4().id(), day, todoPlan.description4().time(), todoPlan.description4().text());
      }
    }
  }

  private void updatePlanDescription(Long id, Day day, String time, String descriptionText) {
    if (descriptionText != null) {
      Optional<Plan> optionalPlan = findById(id);
      if (optionalPlan.isPresent()) {
        Plan plan = optionalPlan.get();
        plan.setDay(day);
        plan.setTime(time);
        plan.setDescription(descriptionText);
        planRepository.save(plan);
      }
    }
  }

  public Map<Day, PlanDescriptions> findAllPlans() {
    List<Plan> plans = planRepository.findAll();
    Map<Day, PlanDescriptions> planMap = new TreeMap<>(Comparator.comparingInt(Day::ordinal));

    for (Day day : Day.values()) {
      List<Plan> dayPlans = plans.stream()
          .filter(plan -> plan.getDay() == day)
          .limit(4)
          .toList();

      Description desc1 = !dayPlans.isEmpty() ? new Description(dayPlans.get(0).getId(), dayPlans.get(0).getTime(), dayPlans.get(0).getDescription()) : new Description(0L, "00:00","");
      Description desc2 = dayPlans.size() > 1 ? new Description(dayPlans.get(1).getId(), dayPlans.get(1).getTime(), dayPlans.get(1).getDescription()) : new Description(0L, "00:00","");
      Description desc3 = dayPlans.size() > 2 ? new Description(dayPlans.get(2).getId(), dayPlans.get(2).getTime(), dayPlans.get(2).getDescription()) : new Description(0L, "00:00","");
      Description desc4 = dayPlans.size() > 3 ? new Description(dayPlans.get(3).getId(), dayPlans.get(3).getTime(), dayPlans.get(3).getDescription()) : new Description(0L, "00:00","");

      PlanDescriptions descriptions = new PlanDescriptions(desc1, desc2, desc3, desc4);
      planMap.put(day, descriptions);
    }

    return planMap;
  }

  public Optional<Plan> findById(Long id) {
    return planRepository.findById(id);
  }

  public List<Plan> findByDay(Day day) {
    return planRepository.findByDay(day);
  }
}
