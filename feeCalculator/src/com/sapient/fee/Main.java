package com.sapient.fee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) {
		FileInputStream fileInputStream= null;
		try {
			fileInputStream = new FileInputStream("E:\\courses\\jsp\\feeCalculator\\feeInput.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(fileInputStream);
		scanner.nextLine();
		String inputPerLine = null;
		List<Transaction> transactions = new ArrayList<>();
		while(scanner.hasNextLine()) {
			inputPerLine = scanner.nextLine();
			String[] strings = inputPerLine.split(",");
			Transaction transaction = new Transaction();
			transaction.setExternalTransactionId(strings[0].trim());
			transaction.setClientId(strings[1].trim());
			transaction.setSecurityId(strings[2].trim());
			transaction.setTransactionType(strings[3].trim());
			transaction.setTransactionDate(strings[4].trim());
			transaction.setMarketValue(strings[5].trim());
			transaction.setPriorityFlag(strings[6].trim());
			transactions.add(transaction);
		}
		
		List<Transaction> transactionsWithHighPriority = new ArrayList<>();
		List<Transaction> transactionsWithLowPriorityBuyDeposit =  new ArrayList<>();
		List<Transaction> transactionsWithLowPrioritySellWithdraw = new ArrayList<>();
		for(Transaction transaction: transactions) {
			if(transaction.getPriorityFlag().equals("Y"))
				transactionsWithHighPriority.add(transaction);
			else if((transaction.getTransactionType().equals("BUY")
					||transaction.getTransactionType().equals("DEPOSIT"))
					&&transaction.getPriorityFlag().equals("N")){
				transactionsWithLowPriorityBuyDeposit.add(transaction);
			}
			else if((transaction.getTransactionType().equals("SELL")
					||transaction.getTransactionType().equals("WITHDRAW"))
					&&transaction.getPriorityFlag().equals("N")) {
				transactionsWithLowPrioritySellWithdraw.add(transaction);
			}
		}
		System.out.println(transactionsWithHighPriority.size());
		System.out.println(transactionsWithLowPriorityBuyDeposit.size());
		System.out.println(transactionsWithLowPrioritySellWithdraw.size());
		
		
		//calculating the intra-day transactions.
		for(int i=0;i<transactions.size();i++) {
			for(int j=i+1;j<transactions.size();j++) {
				if(transactions.get(i).getClientId().equals(transactions.get(j).getClientId())
						&&transactions.get(i).getSecurityId().equals(transactions.get(j).getSecurityId())
						&&transactions.get(i).getTransactionDate().equals(transactions.get(j).getTransactionDate())) {
					if(transactions.get(i).getTransactionType().equals("BUY")) {
						if(transactions.get(j).getTransactionType().equals("SELL")) {
						    transactions.get(i).setIsIntraDayTransaction(true);
						    transactions.get(j).setIsIntraDayTransaction(true);
							break;
						}
					}
					else if(transactions.get(i).getTransactionType().equals("SELL")) {
						if(transactions.get(j).getTransactionType().equals("BUY")) {
						    transactions.get(i).setIsIntraDayTransaction(true);
						    transactions.get(j).setIsIntraDayTransaction(true);
							break;
						}
					}
					else if(transactions.get(i).getTransactionType().equals("DEPOSIT")) {
						if(transactions.get(j).getTransactionType().equals("WITHDRAW")) {
						    transactions.get(i).setIsIntraDayTransaction(true);
						    transactions.get(j).setIsIntraDayTransaction(true);
							break;
						}
					}
					else if(transactions.get(i).getTransactionType().equals("WITHDRAW")) {
						if(transactions.get(j).getTransactionType().equals("DEPOSIT")) {
						    transactions.get(i).setIsIntraDayTransaction(true);
						    transactions.get(j).setIsIntraDayTransaction(true);
							break;
						}
					}
				}
			}
		}
		
		Set<GroupedTransactions> groupedTransactions  = new HashSet<>();
	
		
		for(Transaction transaction:transactionsWithHighPriority) {
			GroupedTransactions groupedTransaction = new GroupedTransactions();
			groupedTransaction.setClientId(transaction.getClientId());
			groupedTransaction.setPriorityFlag(transaction.getPriorityFlag());
			groupedTransaction.setTransactionDate(transaction.getTransactionDate());
			groupedTransaction.setTransactionType(transaction.getTransactionType());
			if(groupedTransactions.add(groupedTransaction)==false) {
				for(GroupedTransactions gt: groupedTransactions) {
					if(gt.getClientId().equals(transaction.getClientId())
			        &&gt.getPriorityFlag().equals(transaction.getPriorityFlag())
					&&gt.getTransactionDate().equals(transaction.getTransactionDate())
					&&gt.getTransactionType().equals(transaction.getTransactionType())){
						gt.setProcessingFee(gt.getProcessingFee()+500);
					}
				}
			}
			else {
				groupedTransaction.setProcessingFee(100);
			}
		}
		
		for(Transaction transaction:transactionsWithLowPriorityBuyDeposit) {
			GroupedTransactions groupedTransaction = new GroupedTransactions();
			groupedTransaction.setClientId(transaction.getClientId());
			groupedTransaction.setPriorityFlag(transaction.getPriorityFlag());
			groupedTransaction.setTransactionDate(transaction.getTransactionDate());
			groupedTransaction.setTransactionType(transaction.getTransactionType());
			if(groupedTransactions.add(groupedTransaction)==false) {
				for(GroupedTransactions gt: groupedTransactions) {
					if(gt.getClientId().equals(transaction.getClientId())
			        &&gt.getPriorityFlag().equals(transaction.getPriorityFlag())
					&&gt.getTransactionDate().equals(transaction.getTransactionDate())
					&&gt.getTransactionType().equals(transaction.getTransactionType())){
						gt.setProcessingFee(gt.getProcessingFee()+50);
					}
				}
			}
			else {
				groupedTransaction.setProcessingFee(500);
			}
		}
		
		for(Transaction transaction:transactionsWithLowPrioritySellWithdraw) {
			GroupedTransactions groupedTransaction = new GroupedTransactions();
			groupedTransaction.setClientId(transaction.getClientId());
			groupedTransaction.setPriorityFlag(transaction.getPriorityFlag());
			groupedTransaction.setTransactionDate(transaction.getTransactionDate());
			groupedTransaction.setTransactionType(transaction.getTransactionType());
			if(groupedTransactions.add(groupedTransaction)==false) {
				for(GroupedTransactions gt: groupedTransactions) {
					if(gt.getClientId().equals(transaction.getClientId())
			        &&gt.getPriorityFlag().equals(transaction.getPriorityFlag())
					&&gt.getTransactionDate().equals(transaction.getTransactionDate())
					&&gt.getTransactionType().equals(transaction.getTransactionType())){
						gt.setProcessingFee(gt.getProcessingFee()+500);
					}
				}
			}
			else {
				groupedTransaction.setProcessingFee(500);
			}
		}
		
		List<GroupedTransactions> output = new ArrayList<>(groupedTransactions);
		Collections.sort(output);
		FileOutputStream fileOutputStream = null;
		try {
		 fileOutputStream = new FileOutputStream("E:\\courses\\jsp\\feeCalculator\\output.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String string = "ClientId,TransactionType,TransactionDate,ProcessingFee\n";
		try {
		fileOutputStream.write(string.getBytes());
		for(GroupedTransactions gt:output) {
		 string =gt.getClientId()+","+gt.getTransactionType()+","+ gt.getTransactionDate()+","
				 +gt.getProcessingFee()+"\n";
		 fileOutputStream.write(string.getBytes());
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
