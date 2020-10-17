package ir.mahmood.sahame.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class UserDto {
    private Integer id;

    @NotEmpty(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;
    private String code;
    private String codeSentAt;
    private String codeSentCount;

    @NotBlank(message = "first_name is required")
    private String firstName;

    @NotBlank(message = "last_name is required")
    private String lastName;
    private Date createdAt;
}
