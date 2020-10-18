package ir.mahmood.sahame.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_sell")
public class UserSellEntity extends BaseEntity<Integer> {

    @Id
    @Column
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private StockEntity stockEntity;

    @Column(name = "sell_count")
    private Integer sellCount;

    @Column(name = "sell_price")
    private Integer sellPrice;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "sell_at")
    private Date sellAt;

    @Column(name = "interest")
    private Long interest;

}
