package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rent")
public class Rent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name="item_no")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name="user_no")
	private User user;
	
	@Column(name="borrow_date")
	private Date borrowDate;
	
	@Column(name="return_date")
	private Date returnDate;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Rent [no=" + no + ", item=" + item + ", user=" + user + ", borrowDate=" + borrowDate + ", returnDate="
				+ returnDate + "]";
	}
}
