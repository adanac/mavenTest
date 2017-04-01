package com.allen;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

	private String name;
	private Integer age;
}
