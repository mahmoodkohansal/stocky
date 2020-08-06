package ir.mahmood.sahame.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity()
@Table(name = "user")
public class UserEntity extends BaseEntity<Integer> {

    @Id
    private Integer id;

    private String username;

    private String password;

    private String mobile;

    private String firstName;

    private String lastName;

    private Date createdAt;

    @OneToMany(
            mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserBuyEntity> userBuyEntities = new ArrayList<UserBuyEntity>();

    @OneToMany(
            mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserSellEntity> userSellEntities = new ArrayList<UserSellEntity>();

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public Integer setId() {
        return null;
    }

}
