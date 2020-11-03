package ir.mahmood.sahame.dto;

import ir.mahmood.sahame.entity.StockEntity;
import ir.mahmood.sahame.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BuyDto extends BaseDto<Integer> {
    private Integer id;
    private StockEntity stockEntity;
    private UserEntity userEntity;

    @NotNull
    private Integer buyCount;

    @NotNull
    private Integer buyPrice;

    @NotNull
    private Integer predictPrice;
    private Long totalPrice;
    private Date buyAt;
}
