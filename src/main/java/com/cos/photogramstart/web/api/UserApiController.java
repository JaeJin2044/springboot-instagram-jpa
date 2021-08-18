package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDTO;
import com.cos.photogramstart.web.dto.user.UserUpdateDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;

	@PutMapping("/api/user/{id}")
	public CMRespDTO<?> update(
			@PathVariable int id ,
			@Valid UserUpdateDTO userUpdateDTO,
			BindingResult bindingResult, //bindingResult는  @Valid 바로 다음에 적어줘야함.
			@AuthenticationPrincipal PrincipalDetails principalDetails
			) 
	{
		if(bindingResult.hasErrors()) {
			Map<String,String> errorMap = new HashMap();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("==========================================");
				System.out.println(error.getField());
				System.out.println(error.getDefaultMessage());
				System.out.println("==========================================");
			}
			throw new CustomValidationApiException("유효성 검사 실패함",errorMap);
		}else {
			User userEntity = userService.userUpdate(id, userUpdateDTO.toEntity());
			principalDetails.setUser(userEntity); //세션 정보 변경 
			
			return new CMRespDTO<>(1,"회원수정 완료",userEntity);
		}
		
		
	}
	
}
