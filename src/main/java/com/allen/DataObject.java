package com.allen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class DataObject {
	private String id;
	private String name;
	private String userId;
	private String password;
}
