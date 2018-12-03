package com.jpmc.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.jpmc.dataObjects.Instruction;
import com.jpmc.exceptions.TradeException;

public class InstructionUtility {
	private static InstructionUtility utility;
	
	private static List<String> exceptionWeekendCurrencies = new ArrayList<>();
	static {
		exceptionWeekendCurrencies.add("AED");
		exceptionWeekendCurrencies.add("SAR");
	}
	private InstructionUtility() {
		
	}
	
	/**
	 * create singleton object of Utility class 
	 * @return Singleton object of Utility class 
	 */
	public static InstructionUtility getInstructionUtility() {
		synchronized (InstructionUtility.class) {
			if(utility == null) {
				utility = new InstructionUtility();
			}
			return utility;
		}
	}
	/**
	 * calculate total Amount of a Trade
	 * @param instruction input received  
	 * @return total amount of a trade
	 */
	public Double getTotalAmount(final Instruction instruction) {
		return instruction.getPrice() * instruction.getUnits() * instruction.getAgreedFx();
	}
	
	/**
	 * set actual settlement Date if input settlement Date is Weekend based on Currency
	 * @param settlementDate input settlement date
	 * @param currency input currency
	 */
	public LocalDate setActualSettlementDate(final LocalDate settlementDate,final String currency) {
		LocalDate actualSettlementDate  = settlementDate;
		if(isExceptionWeekendCurrency(currency)) {
			if(actualSettlementDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
				actualSettlementDate = actualSettlementDate.plusDays(2);
			}else if(actualSettlementDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
				actualSettlementDate = actualSettlementDate.plusDays(1);
			}
		}else {
			if(actualSettlementDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
				actualSettlementDate = actualSettlementDate.plusDays(2);
			}else if(actualSettlementDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				actualSettlementDate = actualSettlementDate.plusDays(1);
			}
		}
		return actualSettlementDate;
	}

	private boolean isExceptionWeekendCurrency(String currency) {
		if(exceptionWeekendCurrencies.contains(currency)) {
			return true;
		}
		return false;
	}
	
	public Instruction parseInput(final String input) throws TradeException {
		
		final Instruction instruction = new Instruction();
		try {
		final String inputArray[] = input.split(TradeConstants.FIELD_DELIMETER);
		instruction.setEntity(inputArray[0]);
		instruction.setBuySellIndicator(inputArray[1]);
		instruction.setAgreedFx(Double.parseDouble(inputArray[2]));
		instruction.setCurrency(inputArray[3]);
		instruction.setInstructionDate(convertDate(inputArray[4]));
		instruction.setSettlementDate(convertDate(inputArray[5]));
		instruction.setUnits(Long.parseLong(inputArray[6]));
		instruction.setPrice(Double.parseDouble(inputArray[7]));
		}catch(Exception e) {
			throw new TradeException("Exception while Parsing Input :"+input);
		}
		return instruction;
		
	}

	private LocalDate convertDate(String dateString) {
		String dateSplit[] = dateString.split(TradeConstants.DATE_DELIMETER);
		final LocalDate date = LocalDate.of(Integer.parseInt(dateSplit[2]), setMonth(dateSplit[1]), 
				Integer.parseInt(dateSplit[0]));
		return date;
	}

	private Month setMonth(String month) {
		switch (month) {
		case "Jan":
			return Month.JANUARY;
		case "Feb":
			return Month.FEBRUARY;
		case "Mar":
			return Month.MARCH;
		case "Apr":
			return Month.APRIL;
		case "May":
			return Month.MAY;
		case "Jun":
			return Month.JUNE;
		case "Jul":
			return Month.JULY;
		case "Aug":
			return Month.AUGUST;
		case "Sep":
			return Month.SEPTEMBER;
		case "Oct":
			return Month.OCTOBER;
		case "Nov":
			return Month.NOVEMBER;
		case "Dec":
			return Month.DECEMBER;
		}
		return null;
	}
}
