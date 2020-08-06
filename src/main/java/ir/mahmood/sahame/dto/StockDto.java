package ir.mahmood.sahame.dto;

import ir.mahmood.sahame.constant.MarketType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StockDto extends BaseDto<Integer> {
    private String tsetmcId;
    private String name;
    private MarketType marketType;
    private String symbol;
}
