package team12.smartstudyplace.mqtt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team12.smartstudyplace.mqtt.Mqtt;

public interface MqttRepository extends JpaRepository<Mqtt, Long> {
}
