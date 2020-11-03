package ir.mahmood.sahame.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_buy")
public class BuyEntity extends BaseEntity<Integer>{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private StockEntity stockEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "buy_count")
    private Integer buyCount;

    @Column(name = "buy_price")
    private Integer buyPrice;

    @Column(name = "predict_price")
    private Integer predictPrice;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "buy_at")
    private Date buyAt;
}
