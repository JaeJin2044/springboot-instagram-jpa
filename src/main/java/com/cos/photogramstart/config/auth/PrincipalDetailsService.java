package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRespository;
	
	// 1.패스워드는 알아서 체킹하니까 신경 쓸 필요 없다.
	// 2.리턴이 잘되면 자동으로 UserDetails 세션을 만든다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//비밀번호는 알아서 처리 해준다.
		//아이디가 있는지 없는지 확인을 해야한다.
		System.out.println("아이디 뭐야 ???  "+username);
		
		User userEntity = userRespository.findByUsername(username);
		
		if( userEntity == null ) {
			return null;
		}else {
			return new PrincipalDetails(userEntity);
		}
	}
}




