package com.jobseek.speedjobs.dto.user;

import com.jobseek.speedjobs.domain.user.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSearchCondition {

	private String name;

	private String nickname;

	private String email;

	private Role role;

}