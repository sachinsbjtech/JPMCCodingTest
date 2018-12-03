package com.jpmc.processor;

import com.jpmc.dataObjects.Instruction;
import com.jpmc.exceptions.TradeException;
import com.jpmc.util.InstructionUtility;

public class InputProcessor {
	private static InstructionUtility utility = InstructionUtility.getInstructionUtility();
	public static Instruction processInput(final String input) {
		try {
			final Instruction inputInstruction = utility.parseInput(input);
			inputInstruction.setActualSellementDate(utility.setActualSettlementDate(inputInstruction.getSettlementDate(), 
					inputInstruction.getCurrency()));
			inputInstruction.setTotalAmount(utility.getTotalAmount(inputInstruction));
			return inputInstruction;
		} catch (TradeException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
