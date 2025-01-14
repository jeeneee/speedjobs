package com.jobseek.speedjobs.domain.member;

import com.jobseek.speedjobs.domain.user.Provider;
import com.jobseek.speedjobs.domain.user.User;
import com.jobseek.speedjobs.domain.user.UserVisitor;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "members")
public class Member extends User {

	private String gender;

	private LocalDate birth;

	private String bio;

	private String oauthId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Provider provider;

	public void updateInfo(Member member) {
		update(member.getName(), member.getNickname(), member.getPicture(),
			member.getContact());
		this.birth = member.birth;
		this.bio = member.bio;
		this.gender = member.gender;
	}

	@Override
	public <T> T accept(UserVisitor<T> visitor) {
		return visitor.visitMember(this);
	}
}
