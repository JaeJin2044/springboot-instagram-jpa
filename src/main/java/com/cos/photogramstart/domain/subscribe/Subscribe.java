package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			name="subscribe_uk",
			columnNames = {"fromUserId","toUserId"}  //실제 데이터 베이스의 컬럼명을 적어야함.
		)
	}
)
public class Subscribe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@JoinColumn(name="fromUserId")   // 이렇게 컬럼명을 만들어라 ... 마음대로 만들지마 !!!
	@ManyToOne
	private User fromUser; //구독하는 유저
	
	@JoinColumn(name="toUserId")
	@ManyToOne
	private User toUser; // 구독받는 유저
	
	private LocalDateTime createDate;
	
	
	@PrePersist //디비에 INSERT되기 직전에 실행 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}

















