package com.sapient.fee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class GroupedTransactions implements Comparable {
	
	private String clientId;
	private String transactionType;
	private String transactionDate;
	private String priorityFlag;
	private Integer processingFee =0;
	
	public GroupedTransactions() {
		super();
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getPriorityFlag() {
		return priorityFlag;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(clientId,priorityFlag,transactionDate,transactionType);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null)
			return false;
		if(obj == this)
			return true;
		if(getClass()!= obj.getClass())
			return false;
		GroupedTransactions transaction =  (GroupedTransactions) obj;
		return (transaction.getClientId().equals(clientId)&&
				transaction.getPriorityFlag().equals(priorityFlag)&&
				transaction.getTransactionDate().equals(transactionDate)&&
				transaction.getTransactionType().equals(transactionType));
	}

	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	public Integer getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(Integer processingFee) {
		this.processingFee = processingFee;
	}

	@Override
	public String toString() {
		return "GroupedTransactions [clientId=" + clientId + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + ", priorityFlag=" + priorityFlag + ", processingFee="
				+ processingFee + "]" +"\n";
	}

	@Override
	public int compareTo(Object o) {
		GroupedTransactions groupedTransaction = (GroupedTransactions) o;
		if(this.getClientId().compareTo(groupedTransaction.getClientId()) <0)
			return -1;
		else if(this.getClientId().compareTo(groupedTransaction.getClientId()) >0)
			return 1;
		else if(this.getClientId().compareTo(groupedTransaction.getClientId()) ==0) {
			if(this.getTransactionType().compareTo(groupedTransaction.getTransactionType()) <0)
				return -1;
			else if(this.getTransactionType().compareTo(groupedTransaction.getTransactionType()) >0)
				return 1;
			else if(this.getTransactionType().compareTo(groupedTransaction.getTransactionType()) ==0) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = dateFormat.parse(this.getTransactionDate());
					date2 = dateFormat.parse(groupedTransaction.getTransactionDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(date1.equals(date2)) {
				
				if(this.getTransactionDate().compareTo(groupedTransaction.getTransactionDate()) <0)
					return -1;
				else if(this.getTransactionDate().compareTo(groupedTransaction.getTransactionDate()) <0)
					return 1;
				else {
					if(this.getPriorityFlag().compareTo(groupedTransaction.getPriorityFlag()) <0)
						return -1;
					else if(this.getPriorityFlag().compareTo(groupedTransaction.getPriorityFlag()) >0)
						return 1;
					return 0;
				}
				}
				if(date1.after(date2))
					return 1;
				else 
					return -1;
			}
		}
		return 0;
	}
	
	

}
