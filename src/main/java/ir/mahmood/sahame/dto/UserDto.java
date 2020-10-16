package ir.mahmood.sahame.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String mobile;
    private String firstName;
    private String lastName;
    private Date createdAt;
}
