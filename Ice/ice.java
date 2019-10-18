package ice;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ice {

	private static String filename = "./data.txt";

	private static ArrayList<data> readData() {
		ArrayList<data> dataSet = new ArrayList<data>();
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String Line = sc.nextLine();
				String[] parts = Line.split(" ");
				data d = new data();
				d.year = Integer.parseInt(parts[0]);
				d.days = Integer.parseInt(parts[1]);
				dataSet.add(d);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found.");
		}
		return dataSet;
	}

	static public void main(String[] args) {
		ArrayList<data> dataSet = readData();
		int flag = Integer.valueOf(args[0]);
		DecimalFormat df = new DecimalFormat("#.##");
		if (flag == 100) {
			for (data d : dataSet) {
				System.out.println(d.year + " " + d.days);
			}
		} else if (flag == 200) {
			System.out.println(dataSet.size());

			System.out.println(df.format(getMean(dataSet)));
			System.out.println(df.format(getStdDev(dataSet)));

		} else if (flag == 300) {
			double sum = 0.0;
			double B0 = Double.parseDouble(args[1]);
			double B1 = Double.parseDouble(args[2]);
			for (data d : dataSet) {
				double x = d.year;
				double y = d.days;
				sum += (B0 + B1 * x - y) * (B0 + B1 * x - y);

			}
			System.out.println(df.format(sum / dataSet.size()));
		} else if (flag == 400) {
			double sum1 = 0.0;
			double sum2 = 0.0;
			double B0 = Double.parseDouble(args[1]);
			double B1 = Double.parseDouble(args[2]);
			for (data d : dataSet) {
				double x = d.year;
				double y = d.days;
				sum1 += (B0 + B1 * x - y);
				sum2 += (B0 + B1 * x - y) * x;
			}
			System.out.println(df.format((sum1 * 2) / dataSet.size()));
			System.out.println(df.format((sum2 * 2) / dataSet.size()));
		} else if (flag == 500) {
			double n = Double.parseDouble(args[1]);
			double T = Double.parseDouble(args[2]);
			double B0 = 0;
			double B1 = 0;

			for (int t = 1; t <= T; t++) {
				double sum1 = 0.0;
				double sum2 = 0.0;
				for (data d : dataSet) {
					double x = d.year;
					double y = d.days;
					sum1 += (B0 + B1 * x - y);
					sum2 += (B0 + B1 * x - y) * x;
				}
				double MSE_dB0 = (sum1 * 2) / dataSet.size();
				double MSE_dB1 = (sum2 * 2) / dataSet.size();
				B0 = B0 - n * MSE_dB0;
				B1 = B1 - n * MSE_dB1;
				double sum = 0.0;
				for (data d : dataSet) {
					double x = d.year;
					double y = d.days;
					sum += (B0 + B1 * x - y) * (B0 + B1 * x - y);

				}
				double MSE = sum/dataSet.size();
				System.out.println(t + " " + df.format(B0) + " " + df.format(B1) + " " + df.format(MSE));
				
			}
		}
		else if(flag == 600){
			double sum1 = 0.0;
			double sum2 = 0.0;
			double x_avg = getMean_x(dataSet);
			double y_avg = getMean(dataSet);
			for(data d:dataSet){
				sum1 += (d.year - x_avg) * (d.days - y_avg);
				sum2 += (d.year - x_avg) * (d.year - x_avg);
			}
			double B1 = sum1/sum2;
			double B0 = y_avg - B1 * x_avg;
			double temp = 0.0;
			for (data d : dataSet) {
				double x = d.year;
				double y = d.days;
				temp += (B0 + B1 * x - y) * (B0 + B1 * x - y);
			}
			double MSE = temp/dataSet.size();
			System.out.println(df.format(B0) + " " + df.format(B1) + " " + df.format(MSE));
		}
		else if(flag == 700){
			double x = Double.parseDouble(args[1]);
			double sum1 = 0.0;
			double sum2 = 0.0;
			double x_avg = getMean_x(dataSet);
			double y_avg = getMean(dataSet);
			for(data d:dataSet){
				sum1 += (d.year - x_avg) * (d.days - y_avg);
				sum2 += (d.year - x_avg) * (d.year - x_avg);
			}
			double B1 = sum1/sum2;
			double B0 = y_avg - B1 * x_avg;
			double y_predict = B0 + B1 * x;
			System.out.println(df.format(y_predict));
		}
		else if(flag == 800){
			double x_avg = getMean_x(dataSet);
			double temp = 0.0;
			for(data d : dataSet){
				temp += (d.year - x_avg)*(d.year - x_avg);
			}
			
			double std = Math.sqrt(temp/(dataSet.size()-1));
			for(data d : dataSet){
				d.year = (d.year - x_avg)/std;
			}
			double n = Double.parseDouble(args[1]);
			double T = Double.parseDouble(args[2]);
			double B0 = 0;
			double B1 = 0;

			for (int t = 1; t <= T; t++) {
				double sum1 = 0.0;
				double sum2 = 0.0;
				for (data d : dataSet) {
					double x = d.year;
					double y = d.days;
					sum1 += (B0 + B1 * x - y);
					sum2 += (B0 + B1 * x - y) * x;
				}
				double MSE_dB0 = (sum1 * 2) / dataSet.size();
				double MSE_dB1 = (sum2 * 2) / dataSet.size();
				B0 = B0 - n * MSE_dB0;
				B1 = B1 - n * MSE_dB1;
				double sum = 0.0;
				for (data d : dataSet) {
					double x = d.year;
					double y = d.days;
					sum += (B0 + B1 * x - y) * (B0 + B1 * x - y);

				}
				double MSE = sum/dataSet.size();
				System.out.println(t + " " + df.format(B0) + " " + df.format(B1) + " " + df.format(MSE));
				
			}
		}
		else if(flag == 900){
			Random ran = new Random();
			
			double x_avg = getMean_x(dataSet);
			double temp = 0.0;
			for(data d : dataSet){
				temp += (d.year - x_avg)*(d.year - x_avg);
			}
			
			double std = Math.sqrt(temp/(dataSet.size()-1));
			for(data d : dataSet){
				d.year = (d.year - x_avg)/std;
			}
			double n = Double.parseDouble(args[1]);
			double T = Double.parseDouble(args[2]);
			double B0 = 0;
			double B1 = 0;

			for (int t = 1; t <= T; t++) {
				int r = ran.nextInt(dataSet.size())+1;
				double xj = dataSet.get(r).year;
				double yj = dataSet.get(r).days;
				double MSE_dB0 = 2*(B0 + B1*xj - yj);
				double MSE_dB1 = 2*(B0 + B1*xj - yj) * xj;
				B0 = B0 - n * MSE_dB0;
				B1 = B1 - n * MSE_dB1;
				double sum = 0.0;
				for (data d : dataSet) {
					double x = d.year;
					double y = d.days;
					sum += (B0 + B1 * x - y) * (B0 + B1 * x - y);

				}
				double MSE = sum/dataSet.size();
				System.out.println(t + " " + df.format(B0) + " " + df.format(B1) + " " + df.format(MSE));
				
			}
		}

	}

	static double getMean(ArrayList<data> dataSet) {
		double sum = 0.0;
		for (data a : dataSet)
			sum += a.days;
		return sum / dataSet.size();
	}
	static double getMean_x(ArrayList<data> dataSet) {
		double sum = 0.0;
		for (data a : dataSet)
			sum += a.year;
		return sum / dataSet.size();
	}

	static double getVariance(ArrayList<data> dataSet) {
		double mean = getMean(dataSet);
		double temp = 0;
		for (data a : dataSet)
			temp += (a.days - mean) * (a.days - mean);
		return temp / (dataSet.size() - 1);
	}

	static double getStdDev(ArrayList<data> dataSet) {
		return Math.sqrt(getVariance(dataSet));
	}

}

class data {
	double year;
	int days;

}
