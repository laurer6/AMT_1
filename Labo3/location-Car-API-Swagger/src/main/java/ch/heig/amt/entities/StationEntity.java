package ch.heig.amt.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class StationEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private String address;
}
