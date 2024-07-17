package team12.smartstudyplace.plan;

import jakarta.persistence.*;
import lombok.*;

import team12.smartstudyplace.plan.value.Day;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name="plans")
public class Plan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  //@Enumerated(EnumType.STRING)
  private Day day;

  private String time;

  private String description;
}
