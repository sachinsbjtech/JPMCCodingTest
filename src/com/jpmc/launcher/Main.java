package com.jpmc.launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jpmc.reporting.ReportGeneration;

public class Main {

	public static void main(String[] args) {
		
		List<String> inputList = new ArrayList<>();
		//read input data from file for this example
		try {
			final String inputFile = Main.class.getResource("inputfile.txt").getFile();
			final BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String inputLine="";
			while((inputLine=br.readLine())!=null) {
				inputList.add(inputLine);
			}
			br.close();
			
			//Process row data and print report
			final ReportGeneration reportGeneration = new ReportGeneration();
			reportGeneration.processInputAndGenerateReport(inputList);
			
		} catch (IOException e) {
			System.out.print("Error input Data file :"+e.getMessage());
		}
	}

}
