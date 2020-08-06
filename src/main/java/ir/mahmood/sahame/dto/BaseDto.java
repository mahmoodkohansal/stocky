package ir.mahmood.sahame.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseDto<ID extends Serializable> implements Serializable {
    protected ID id;

    public BaseDto() {
    }
}
