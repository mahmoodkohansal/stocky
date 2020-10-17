package ir.mahmood.sahame.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    private String code;

    private Date codeSentAt;

    private Integer codeSentCount;

    @NotBlank(message = "first_name is required")
    private String firstName;

    @NotBlank(message = "last_name is required")
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
