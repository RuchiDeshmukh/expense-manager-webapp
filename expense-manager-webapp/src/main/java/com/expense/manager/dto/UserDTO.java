package com.expense.manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@NotBlank(message="User name should not be null")
	private String name;
	
	@Email(message="Invalid email address")
	private String email;
	
	@NotBlank(message="Password should not be null")
	@Size(min=5,message = "Password should be minimum {min} characters")
	private String password;
	
	private String confirmpassword;

}
