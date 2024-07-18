package team12.smartstudyplace.mqtt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team12.smartstudyplace.mqtt.Mqtt;

import java.util.List;
import java.util.Optional;

public interface MqttRepository extends JpaRepository<Mqtt, Long> {
  public List<Mqtt> findAll();
  public Optional<Mqtt> findById(Long id);
  Optional<Mqtt> findTopByOrderByIdDesc();


}
