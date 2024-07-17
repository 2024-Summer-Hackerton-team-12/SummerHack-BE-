package team12.smartstudyplace.plan.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import team12.smartstudyplace.plan.value.Day;

import java.util.Map;

@Getter
@Setter
@Builder
public class PlanResponse {
  private Map<Day, PlanDescriptions> plans;
}
