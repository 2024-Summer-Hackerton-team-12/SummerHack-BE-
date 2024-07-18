package team12.smartstudyplace.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team12.smartstudyplace.mqtt.service.MqttService;
import team12.smartstudyplace.plan.presentation.dto.PlanDescriptions;
import team12.smartstudyplace.plan.presentation.dto.PlanRequest;
import team12.smartstudyplace.plan.presentation.dto.PlanResponse;
import team12.smartstudyplace.plan.service.PlanService;
import team12.smartstudyplace.plan.value.Day;

import java.util.Map;

@RestController
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService planService;
    private final MqttService mqttService;
    private final MqttService.MqttGateway mqttGateway;

    @Autowired
    public PlanController(PlanService planService, MqttService mqttService, MqttService.MqttGateway mqttGateway) {
      this.planService = planService;
      this.mqttService = mqttService;
      this.mqttGateway = mqttGateway;
    }


    @GetMapping
    public ResponseEntity<PlanResponse> getPlan() {
      Map<Day, PlanDescriptions> plans = planService.findAllPlans();
      PlanResponse planResponse = PlanResponse.builder().plans(plans).build();
      return ResponseEntity.ok(planResponse);
    }



    @PostMapping
      public void createPlan(@RequestBody PlanRequest planRequest) {
    planService.save(planRequest);
  }
}

