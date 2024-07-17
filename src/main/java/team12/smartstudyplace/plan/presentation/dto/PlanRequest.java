package team12.smartstudyplace.plan.presentation.dto;

import team12.smartstudyplace.plan.value.Day;

import java.util.Map;

public record PlanRequest(
    Map<Day, PlanDescriptions> plans
) {
}
