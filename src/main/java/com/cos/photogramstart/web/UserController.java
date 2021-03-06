package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "user/profile";
	} 
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails,Model model) {
		
		// 1.추천
		//System.out.println("세션정보 : "+principalDetails.getUser());
		// 2.복잡한 방법(극혐)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
		//System.out.println("직접 찾은 세션정보 : "+mPrincipalDetails.getUser());
		
		/* model.addAttribute("principal", principalDetails.getUser()); */
		
		// =======================================================================
		
		return "user/update";
	}
}
