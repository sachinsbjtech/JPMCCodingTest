package com.jpmc.reporting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmc.dataObjects.Instruction;
import com.jpmc.processor.InputProcessor;
import com.jpmc.util.TradeConstants;

public class ReportGeneration {
	private List<Instruction> incoming = new ArrayList<>();
	private List<Instruction> outgoing = new ArrayList<>();
	/**
	 * process input and generate report
	 * @param inputData
	 */
	public void processInputAndGenerateReport(final List<String> inputData) {
		// process input data
		final Map<String, List<Instruction>> tradeMap = new HashMap<>();
		for(String input : inputData) {
			Instruction instruction = InputProcessor.processInput(input);
			if(instruction!=null) {
				if(TradeConstants.BUY.equalsIgnoreCase(instruction.getBuySellIndicator())) {
					outgoing.add(instruction);
				}else {
					incoming.add(instruction);
				}
			}
		}
		
		//Prepare map of all input data
		tradeMap.put(TradeConstants.INCOMING, incoming);
		tradeMap.put(TradeConstants.OUTGOING, outgoing);
		
		//Print Final Report
		PrintingReport.generateReport(tradeMap);
		
	}

}
