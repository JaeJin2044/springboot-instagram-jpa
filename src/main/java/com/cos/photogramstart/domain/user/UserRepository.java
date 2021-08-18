package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 JpaRespository를 상속한다면 IOC등록이 자동으로 된다.
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// JPA query method
	// 기본적인 api는 있지만 복잡한 쿼리 까지 수행 할수 없음
	
	User findByUsername(String username);

}
