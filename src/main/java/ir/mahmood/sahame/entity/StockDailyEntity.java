package ir.mahmood.sahame.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "stock_daily")
public class StockDailyEntity extends BaseEntity<Integer> {

    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private StockEntity stockEntity;

    @Column(name = "last_trade_price")
    private Integer lastTradePrice;

    @Column(name = "closing_price")
    private Integer closingPrice;

    @Column(name = "low_price")
    private Integer lowPrice;

    @Column(name = "high_price")
    private Integer highPrice;

    @Column(name = "yesterday_price")
    private Integer yesterdayPrice;
}
