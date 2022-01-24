package ch.heig.amt.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class EmplacementId implements Serializable {
    private static final long serialVersionUID = 274623388861060791L;
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "station_id", nullable = false)
    private Integer stationId;
}
