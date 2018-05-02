package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public abstract class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@Column(name="title")
	private String title;
	
	@Column(name="is_rent")
	private boolean isRent;
	
	@ManyToOne
	@JoinColumn(name="category_no")
	private Category category;
	
	@Column(name="type", updatable=false, insertable=false)
	private String type;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsRent() {
		return isRent;
	}

	public void setIsRent(boolean isRent) {
		this.isRent = isRent;
	}

	public Category getCategory() {
		return category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
