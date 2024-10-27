package com.example.time_logging.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
