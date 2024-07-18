package team12.smartstudyplace.mqtt.presentation.dto;

public record MqttRequest(
    String clientId,
    double humidity,
    double temperature,
    String ledStatus
) {

}
