package ir.mahmood.sahame.entity;

import java.io.Serializable;

public abstract class BaseEntity<ID extends Serializable> {
    public abstract ID getId();
    public abstract void setId(ID id);
}
