package ir.mahmood.sahame.dto;

import ir.mahmood.sahame.entity.StockEntity;
import ir.mahmood.sahame.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserBuyDto extends BaseDto<Integer> {
    private Integer id;
    private StockEntity stockEntity;
    private UserEntity userEntity;
    private Integer buyCount;
    private Integer buyPrice;
    private Integer predictPrice;
    private Long totalPrice;
    private Date buyAt;
}
