package ir.mahmood.sahame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.mahmood.sahame.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {

    public interface RegisterValidation {};

    private Integer id;

    @NotEmpty(message = "username is required")
    private String username;

    @NotBlank(message = "password is required", groups = { RegisterValidation.class })
    private String password;

    @NotBlank(message = "code is required", groups = { RegisterValidation.class })
    private String code;

    private String codeSentAt;

    private String codeSentCount;

    @NotBlank(message = "first_name is required", groups = { RegisterValidation.class })
    @JsonProperty(value = "first_name")
    private String firstName;

    @NotBlank(message = "last_name is required", groups = { RegisterValidation.class })
    @JsonProperty(value = "last_name")
    private String lastName;

    private Date createdAt;
}
