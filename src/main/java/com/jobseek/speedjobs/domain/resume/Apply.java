package com.jobseek.speedjobs.domain.resume;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import com.jobseek.speedjobs.common.exception.IllegalParameterException;
import com.jobseek.speedjobs.domain.BaseTimeEntity;
import com.jobseek.speedjobs.domain.recruit.Recruit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = PROTECTED)
@Table(name = "applies")
public class Apply extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "apply_id")
	private Long id;

	private Long memberId;

	private Long companyId;

	@ManyToOne(fetch = LAZY, cascade = {PERSIST, MERGE})
	@JoinColumn(name = "resume_id")
	private Resume resume;

	@ManyToOne(fetch = LAZY, cascade = {PERSIST, MERGE})
	@JoinColumn(name = "recruit_id")
	private Recruit recruit;

	@Builder
	private Apply(Long id, Resume resume, Recruit recruit, Long memberId, Long companyId) {
		validateParams(resume, recruit, memberId, companyId);
		this.id = id;
		this.resume = resume;
		this.recruit = recruit;
		this.memberId = memberId;
		this.companyId = companyId;
	}

	private void validateParams(Resume resume, Recruit recruit, Long memberId, Long companyId) {
		if (ObjectUtils.anyNull(resume, recruit, memberId, companyId)) {
			throw new IllegalParameterException();
		}
	}
}
