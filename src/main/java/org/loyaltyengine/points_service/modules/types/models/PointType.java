package org.loyaltyengine.points_service.modules.types.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "point_types")
public class PointType {
    @Id
    private String pointTypeId;
    private Integer numberOfPoints;
}
