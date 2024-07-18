package team12.smartstudyplace.mqtt.presentation.dto;

public record MqttResponse(
    double humidity,
    double temperature,
    String ledStatus
) {
}
