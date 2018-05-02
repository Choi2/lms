package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("음반")
@PrimaryKeyJoinColumn(name="album_no")
public class Album extends Item{
	private String singer;

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "Album [singer=" + singer + ", getNo()=" + getNo() + ", getTitle()=" + getTitle() + "]";
	}

}
