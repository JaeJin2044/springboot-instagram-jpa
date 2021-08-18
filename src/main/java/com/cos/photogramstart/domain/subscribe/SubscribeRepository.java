package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	@Modifying //INSERT, DELETE, UPDATE를 네이티비 쿼리를 작성하려면 해당 어노테이션 필요 !!
	@Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())",nativeQuery =true)
	void addSubscribe(int fromUserId, int toUserId); // 1(변경된 행의 개수) , 실패 -1 , 변경된 행 X 0  
	
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId",nativeQuery = true)
	void unSubscribe(int fromUserId, int toUserId);

}
