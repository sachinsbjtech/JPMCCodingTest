package com.jpmc.reporting;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.jpmc.dataObjects.Instruction;
import com.jpmc.util.TradeConstants;

public class PrintingReport {
	/**
	 * print reports 
	 * @param tradeData 
	 */
	public static void generateReport(final Map<String,List<Instruction>> tradeData) {
		printSettledAmount(tradeData.get(TradeConstants.INCOMING),TradeConstants.INCOMING);
		printSettledAmount(tradeData.get(TradeConstants.OUTGOING),TradeConstants.OUTGOING);
		printRanking(tradeData.get(TradeConstants.INCOMING),TradeConstants.INCOMING);
		printRanking(tradeData.get(TradeConstants.OUTGOING),TradeConstants.OUTGOING);
	}

	/*
	 * print Ranking for incoming and outgoing
	 */
	private static void printRanking(List<Instruction> list,final String settlementType) {
		final Map<String, Double> entityMap = new HashMap<>();
		for(Instruction instruction : list) {
			if(entityMap.get(instruction.getEntity())!=null) {
				//if multiple trades happened for same entity 
				entityMap.put(instruction.getEntity(),(entityMap.get(instruction.getEntity()) + instruction.getTotalAmount()));
			}else {
				entityMap.put(instruction.getEntity(), instruction.getTotalAmount());
			}
		}
		// sort entity map based on TotoalAmount
		final Map<String, Double> sortedMap = entityMap.entrySet().stream()
		.sorted(Map.Entry.<String,Double>comparingByValue().reversed())
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap<String,Double>::new));
		
		System.out.println(settlementType +" Ranking :\n");
		Set<String> keySet = sortedMap.keySet();
		int rank = 1;
		for(String key : keySet) {
			System.out.println(key +" - "+ rank++);
		}
	}
 
	/*
	 * print settlement amount for incoming and outgoing
	 */
	private static void printSettledAmount(final List<Instruction> list,final String settlementType) {
		final LocalDate today = LocalDate.now();
		final double totalAmount = list.stream().filter(l -> l.getActualSellementDate().compareTo(today)==0)
		.mapToDouble(i -> i.getTotalAmount()).sum();
		System.out.println(settlementType+" Amount Settled :"+totalAmount);
		
	}
}
