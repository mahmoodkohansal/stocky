package ir.mahmood.sahame.entity;

import ir.mahmood.sahame.constant.MarketType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "stock")
public class StockEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tsetmc_id")
    private String tsetmcId;

    private String symbol;

    private String name;

    @Embedded
    @Column(name = "market_type")
    private MarketType marketType;

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public Integer setId() {
        return null;
    }

}
