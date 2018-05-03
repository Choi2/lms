package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("dvd")
@PrimaryKeyJoinColumn(name="dvd_no")
public class DVD extends Item{
	
	@Column(name="running_time")
	private int runningTime;

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	@Override
	public String toString() {
		return "DVD [runningTime=" + runningTime + ", getNo()=" + getNo() + ", getTitle()=" + getTitle() + "]";
	}

}
