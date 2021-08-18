package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDTO {
	@NotBlank
	private String name; //필수
	@NotBlank
	private String password; //필수
	
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	//조금 위험함. 코드수정이 필요할 예정(필수값 아닌것도 넣고 있음)
	public User toEntity() {
		return User.builder()
				.name(name)  // 이름을 기재 안헀으면 문제!!! Validation 체크 
				.password(password) //패스워드를 안했으면 문제!!! Validation 체크를 해야함 
				.website(website)
				.bio(bio)
				.gender(gender)
				.phone(phone)
				.build();
	}
	
	
}




