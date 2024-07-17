package team12.smartstudyplace.plan.presentation.dto;

import team12.smartstudyplace.plan.value.Day;

import java.util.List;
import java.util.Map;

public record UpdatePlanRequest(Map<Day, List<String>> plans) {
  // record 선언만으로 생성자, getter, equals(), hashCode(), toString() 메서드가 자동으로 생성됩니다.

  // 필요한 경우 추가 메서드를 여기에 정의할 수 있습니다.
}