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

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="reserve")
public class Reserve {
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

	@Column(name="reverse_rank")
	@ColumnDefault("0")
	private int reverseRank;

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

	public int getReverseRank() {
		return reverseRank;
	}

	public void setReverseRank(int reverseRank) {
		this.reverseRank = reverseRank;
	}

	@Override
	public String toString() {
		return "Reverse [no=" + no + ", item=" + item + ", user=" + user + ", borrowDate=" + borrowDate
				+ ", returnDate=" + returnDate + ", reverseRank=" + reverseRank + "]";
	}
}
