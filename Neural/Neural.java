import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Neural {
	private static String eval_filename = "./hw2_midterm_A_eval.txt";
	private static String train_filename = "./hw2_midterm_A_train.txt";
	private static String test_filename = "./hw2_midterm_A_test.txt";

	private static ArrayList<data> readData(String filename) {
		ArrayList<data> dataSet = new ArrayList<data>();
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				data d = new data();
				d.hw2 = sc.nextDouble();
				d.midterm = sc.nextDouble();
				d.Apass = sc.nextDouble();
				dataSet.add(d);

			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found.");
		}
		return dataSet;
	}

	static public void main(String[] args) {
		int flag = Integer.valueOf(args[0]);
		ArrayList<data> eval_data = readData(eval_filename);
		ArrayList<data> train_data = readData(train_filename);
		ArrayList<data> test_data = readData(test_filename);
		ArrayList<Double> weights = new ArrayList<Double>();
		for (int i = 1; i <= 9; i++) {
			weights.add(Double.parseDouble(args[i]));
		}
		DecimalFormat df = new DecimalFormat("0.00000");
		if (flag == 100) {
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
			double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
			double va = Math.max(ua, 0);
			double vb = Math.max(ub, 0);
			double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
			double vc = 1 / (Math.exp(uc * (-1)) + 1);
			System.out.println(df.format(ua) + " " + df.format(va) + " " + df.format(ub) + " " + df.format(vb) + " "
					+ df.format(uc) + " " + df.format(vc));

		} else if (flag == 200) {
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double y = Double.parseDouble(args[12]);
			double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
			double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
			double va = Math.max(ua, 0);
			double vb = Math.max(ub, 0);
			double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
			double vc = 1 / (Math.exp(uc * (-1)) + 1);
			double E = 0.5 * (vc - y) * (vc - y);
			double dE_dvc = vc - y;
			double dE_duc = dE_dvc * vc * (1 - vc);
			System.out.println(df.format(E) + " " + df.format(dE_dvc) + " " + df.format(dE_duc));
		} else if (flag == 300) {
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double y = Double.parseDouble(args[12]);
			double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
			double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
			double va = Math.max(ua, 0);
			double vb = Math.max(ub, 0);
			double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
			double vc = 1 / (Math.exp(uc * (-1)) + 1);
			double E = 0.5 * (vc - y) * (vc - y);
			double dva_dua;
			double dvb_dub;
			if (ua >= 0) {
				dva_dua = 1;
			} else {
				dva_dua = 0;
			}
			if (ub >= 0) {
				dvb_dub = 1;
			} else {
				dvb_dub = 0;
			}
			double dE_dvc = vc - y;
			double dE_duc = dE_dvc * vc * (1 - vc);
			double dE_dva = weights.get(7) * dE_duc;
			double dE_dua = dE_dva * dva_dua;
			double dE_dvb = weights.get(8) * dE_duc;
			double dE_dub = dE_dvb * dvb_dub;
			System.out.println(
					df.format(dE_dva) + " " + df.format(dE_dua) + " " + df.format(dE_dvb) + " " + df.format(dE_dub));
		} else if (flag == 400) {
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double y = Double.parseDouble(args[12]);
			double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
			double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
			double va = Math.max(ua, 0);
			double vb = Math.max(ub, 0);
			double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
			double vc = 1 / (Math.exp(uc * (-1)) + 1);
			double E = 0.5 * (vc - y) * (vc - y);
			double dva_dua;
			double dvb_dub;
			if (ua >= 0) {
				dva_dua = 1;
			} else {
				dva_dua = 0;
			}
			if (ub >= 0) {
				dvb_dub = 1;
			} else {
				dvb_dub = 0;
			}
			double dE_dvc = vc - y;
			double dE_duc = dE_dvc * vc * (1 - vc);
			double dE_dva = weights.get(7) * dE_duc;
			double dE_dua = dE_dva * dva_dua;
			double dE_dvb = weights.get(8) * dE_duc;
			double dE_dub = dE_dvb * dvb_dub;
			double dE_dw1 = 1 * dE_dua;
			double dE_dw2 = x1 * dE_dua;
			double dE_dw3 = x2 * dE_dua;
			double dE_dw4 = 1 * dE_dub;
			double dE_dw5 = x1 * dE_dub;
			double dE_dw6 = x2 * dE_dub;
			double dE_dw7 = 1 * dE_duc;
			double dE_dw8 = va * dE_duc;
			double dE_dw9 = vb * dE_duc;
			System.out.println(df.format(dE_dw1) + " " + df.format(dE_dw2) + " " + df.format(dE_dw3) + " "
					+ df.format(dE_dw4) + " " + df.format(dE_dw5) + " " + df.format(dE_dw6) + " " + df.format(dE_dw7)
					+ " " + df.format(dE_dw8) + " " + df.format(dE_dw9));

		} else if (flag == 500) {
			double x1 = Double.parseDouble(args[10]);
			double x2 = Double.parseDouble(args[11]);
			double y = Double.parseDouble(args[12]);
			double n = Double.parseDouble(args[13]);
			double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
			double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
			double va = Math.max(ua, 0);
			double vb = Math.max(ub, 0);
			double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
			double vc = 1 / (Math.exp(uc * (-1)) + 1);
			double E = 0.5 * (vc - y) * (vc - y);
			double dva_dua;
			double dvb_dub;
			if (ua >= 0) {
				dva_dua = 1;
			} else {
				dva_dua = 0;
			}
			if (ub >= 0) {
				dvb_dub = 1;
			} else {
				dvb_dub = 0;
			}
			double dE_dvc = vc - y;
			double dE_duc = dE_dvc * vc * (1 - vc);
			double dE_dva = weights.get(7) * dE_duc;
			double dE_dua = dE_dva * dva_dua;
			double dE_dvb = weights.get(8) * dE_duc;
			double dE_dub = dE_dvb * dvb_dub;
			double dE_dw1 = 1 * dE_dua;
			double dE_dw2 = x1 * dE_dua;
			double dE_dw3 = x2 * dE_dua;
			double dE_dw4 = 1 * dE_dub;
			double dE_dw5 = x1 * dE_dub;
			double dE_dw6 = x2 * dE_dub;
			double dE_dw7 = 1 * dE_duc;
			double dE_dw8 = va * dE_duc;
			double dE_dw9 = vb * dE_duc;
			ArrayList<Double> new_weights = new ArrayList<Double>();
			new_weights.add(weights.get(0) - n * dE_dw1);
			new_weights.add(weights.get(1) - n * dE_dw2);
			new_weights.add(weights.get(2) - n * dE_dw3);
			new_weights.add(weights.get(3) - n * dE_dw4);
			new_weights.add(weights.get(4) - n * dE_dw5);
			new_weights.add(weights.get(5) - n * dE_dw6);
			new_weights.add(weights.get(6) - n * dE_dw7);
			new_weights.add(weights.get(7) - n * dE_dw8);
			new_weights.add(weights.get(8) - n * dE_dw9);
			double new_ua = 1 * new_weights.get(0) + x1 * new_weights.get(1) + x2 * new_weights.get(2);
			double new_ub = 1 * new_weights.get(3) + x1 * new_weights.get(4) + x2 * new_weights.get(5);
			double new_va = Math.max(new_ua, 0);
			double new_vb = Math.max(new_ub, 0);
			double new_uc = 1 * new_weights.get(6) + new_va * new_weights.get(7) + new_vb * new_weights.get(8);
			double new_vc = 1 / (Math.exp(new_uc * (-1)) + 1);
			double new_E = 0.5 * (new_vc - y) * (new_vc - y);
			System.out.println(df.format(weights.get(0)) + " " + df.format(weights.get(1)) + " "
					+ df.format(weights.get(2)) + " " + df.format(weights.get(3)) + " " + df.format(weights.get(4))
					+ " " + df.format(weights.get(5)) + " " + df.format(weights.get(6)) + " "
					+ df.format(weights.get(7)) + " " + df.format(weights.get(8)));
			System.out.println(df.format(E));
			System.out.println(df.format(new_weights.get(0)) + " " + df.format(new_weights.get(1)) + " "
					+ df.format(new_weights.get(2)) + " " + df.format(new_weights.get(3)) + " "
					+ df.format(new_weights.get(4)) + " " + df.format(new_weights.get(5)) + " "
					+ df.format(new_weights.get(6)) + " " + df.format(new_weights.get(7)) + " "
					+ df.format(new_weights.get(8)));
			System.out.println(df.format(new_E));
		} else if (flag == 600) {
			for (int i = 0; i < train_data.size(); i++) {
				double x1 = train_data.get(i).hw2;
				double x2 = train_data.get(i).midterm;
				double y = train_data.get(i).Apass;
				double n = Double.parseDouble(args[10]);
				double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
				double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
				double va = Math.max(ua, 0);
				double vb = Math.max(ub, 0);
				double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
				double vc = 1 / (Math.exp(uc * (-1)) + 1);
				double E = 0.5 * (vc - y) * (vc - y);
				double dva_dua;
				double dvb_dub;
				if (ua >= 0) {
					dva_dua = 1;
				} else {
					dva_dua = 0;
				}
				if (ub >= 0) {
					dvb_dub = 1;
				} else {
					dvb_dub = 0;
				}
				double dE_dvc = vc - y;
				double dE_duc = dE_dvc * vc * (1 - vc);
				double dE_dva = weights.get(7) * dE_duc;
				double dE_dua = dE_dva * dva_dua;
				double dE_dvb = weights.get(8) * dE_duc;
				double dE_dub = dE_dvb * dvb_dub;
				double dE_dw1 = 1 * dE_dua;
				double dE_dw2 = x1 * dE_dua;
				double dE_dw3 = x2 * dE_dua;
				double dE_dw4 = 1 * dE_dub;
				double dE_dw5 = x1 * dE_dub;
				double dE_dw6 = x2 * dE_dub;
				double dE_dw7 = 1 * dE_duc;
				double dE_dw8 = va * dE_duc;
				double dE_dw9 = vb * dE_duc;
				ArrayList<Double> new_weights = new ArrayList<Double>();
				new_weights.add(weights.get(0) - n * dE_dw1);
				new_weights.add(weights.get(1) - n * dE_dw2);
				new_weights.add(weights.get(2) - n * dE_dw3);
				new_weights.add(weights.get(3) - n * dE_dw4);
				new_weights.add(weights.get(4) - n * dE_dw5);
				new_weights.add(weights.get(5) - n * dE_dw6);
				new_weights.add(weights.get(6) - n * dE_dw7);
				new_weights.add(weights.get(7) - n * dE_dw8);
				new_weights.add(weights.get(8) - n * dE_dw9);
				double E_sum = 0;
				for (int j = 0; j < eval_data.size(); j++) {
					double temp_vc = calculate_vc(new_weights, eval_data.get(j).hw2, eval_data.get(j).midterm,
							eval_data.get(j).Apass);
					E_sum += 0.5 * (temp_vc - eval_data.get(j).Apass) * (temp_vc - eval_data.get(j).Apass);
				}
				System.out.println(df.format(x1) + " " + df.format(x2) + " " + df.format(y));
				System.out.println(df.format(new_weights.get(0)) + " " + df.format(new_weights.get(1)) + " "
						+ df.format(new_weights.get(2)) + " " + df.format(new_weights.get(3)) + " "
						+ df.format(new_weights.get(4)) + " " + df.format(new_weights.get(5)) + " "
						+ df.format(new_weights.get(6)) + " " + df.format(new_weights.get(7)) + " "
						+ df.format(new_weights.get(8)));
				System.out.println(df.format(E_sum));
				weights = new_weights;
			}

		} else if (flag == 700) {
			int T = Integer.parseInt(args[11]);
			double n = Double.parseDouble(args[10]);
			for (int t = 0; t < T; t++) {
				for (int i = 0; i < train_data.size(); i++) {
					double x1 = train_data.get(i).hw2;
					double x2 = train_data.get(i).midterm;
					double y = train_data.get(i).Apass;
					double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
					double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
					double va = Math.max(ua, 0);
					double vb = Math.max(ub, 0);
					double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
					double vc = 1 / (Math.exp(uc * (-1)) + 1);
					double E = 0.5 * (vc - y) * (vc - y);
					double dva_dua;
					double dvb_dub;
					if (ua >= 0) {
						dva_dua = 1;
					} else {
						dva_dua = 0;
					}
					if (ub >= 0) {
						dvb_dub = 1;
					} else {
						dvb_dub = 0;
					}
					double dE_dvc = vc - y;
					double dE_duc = dE_dvc * vc * (1 - vc);
					double dE_dva = weights.get(7) * dE_duc;
					double dE_dua = dE_dva * dva_dua;
					double dE_dvb = weights.get(8) * dE_duc;
					double dE_dub = dE_dvb * dvb_dub;
					double dE_dw1 = 1 * dE_dua;
					double dE_dw2 = x1 * dE_dua;
					double dE_dw3 = x2 * dE_dua;
					double dE_dw4 = 1 * dE_dub;
					double dE_dw5 = x1 * dE_dub;
					double dE_dw6 = x2 * dE_dub;
					double dE_dw7 = 1 * dE_duc;
					double dE_dw8 = va * dE_duc;
					double dE_dw9 = vb * dE_duc;
					ArrayList<Double> new_weights = new ArrayList<Double>();
					new_weights.add(weights.get(0) - n * dE_dw1);
					new_weights.add(weights.get(1) - n * dE_dw2);
					new_weights.add(weights.get(2) - n * dE_dw3);
					new_weights.add(weights.get(3) - n * dE_dw4);
					new_weights.add(weights.get(4) - n * dE_dw5);
					new_weights.add(weights.get(5) - n * dE_dw6);
					new_weights.add(weights.get(6) - n * dE_dw7);
					new_weights.add(weights.get(7) - n * dE_dw8);
					new_weights.add(weights.get(8) - n * dE_dw9);
					double E_sum = 0;
					for (int j = 0; j < eval_data.size(); j++) {
						double temp_vc = calculate_vc(new_weights, eval_data.get(j).hw2, eval_data.get(j).midterm,
								eval_data.get(j).Apass);
						E_sum += 0.5 * (temp_vc - eval_data.get(j).Apass) * (temp_vc - eval_data.get(j).Apass);
					}
					if(i+1 == train_data.size()){
					System.out.println(df.format(new_weights.get(0)) + " " + df.format(new_weights.get(1)) + " "
							+ df.format(new_weights.get(2)) + " " + df.format(new_weights.get(3)) + " "
							+ df.format(new_weights.get(4)) + " " + df.format(new_weights.get(5)) + " "
							+ df.format(new_weights.get(6)) + " " + df.format(new_weights.get(7)) + " "
							+ df.format(new_weights.get(8)));
					System.out.println(df.format(E_sum));
					}
					weights = new_weights;
				}
			}
		}
		else if (flag == 800) {
			int T = Integer.parseInt(args[11]);
			double n = Double.parseDouble(args[10]);
			double old_E_sum = 0;
			outerloop:
			for (int t = 0; t < T; t++) {
				
				for (int i = 0; i < train_data.size(); i++) {
					double x1 = train_data.get(i).hw2;
					double x2 = train_data.get(i).midterm;
					double y = train_data.get(i).Apass;
					double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
					double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
					double va = Math.max(ua, 0);
					double vb = Math.max(ub, 0);
					double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
					double vc = 1 / (Math.exp(uc * (-1)) + 1);
					double E = 0.5 * (vc - y) * (vc - y);
					double dva_dua;
					double dvb_dub;
					if (ua >= 0) {
						dva_dua = 1;
					} else {
						dva_dua = 0;
					}
					if (ub >= 0) {
						dvb_dub = 1;
					} else {
						dvb_dub = 0;
					}
					double dE_dvc = vc - y;
					double dE_duc = dE_dvc * vc * (1 - vc);
					double dE_dva = weights.get(7) * dE_duc;
					double dE_dua = dE_dva * dva_dua;
					double dE_dvb = weights.get(8) * dE_duc;
					double dE_dub = dE_dvb * dvb_dub;
					double dE_dw1 = 1 * dE_dua;
					double dE_dw2 = x1 * dE_dua;
					double dE_dw3 = x2 * dE_dua;
					double dE_dw4 = 1 * dE_dub;
					double dE_dw5 = x1 * dE_dub;
					double dE_dw6 = x2 * dE_dub;
					double dE_dw7 = 1 * dE_duc;
					double dE_dw8 = va * dE_duc;
					double dE_dw9 = vb * dE_duc;
					ArrayList<Double> new_weights = new ArrayList<Double>();
					new_weights.add(weights.get(0) - n * dE_dw1);
					new_weights.add(weights.get(1) - n * dE_dw2);
					new_weights.add(weights.get(2) - n * dE_dw3);
					new_weights.add(weights.get(3) - n * dE_dw4);
					new_weights.add(weights.get(4) - n * dE_dw5);
					new_weights.add(weights.get(5) - n * dE_dw6);
					new_weights.add(weights.get(6) - n * dE_dw7);
					new_weights.add(weights.get(7) - n * dE_dw8);
					new_weights.add(weights.get(8) - n * dE_dw9);
					
					double E_sum = 0;
					for (int j = 0; j < eval_data.size(); j++) {
						double temp_vc = calculate_vc(new_weights, eval_data.get(j).hw2, eval_data.get(j).midterm,
								eval_data.get(j).Apass);
						E_sum += 0.5 * (temp_vc - eval_data.get(j).Apass) * (temp_vc - eval_data.get(j).Apass);
					}
					if(E_sum>=old_E_sum && i+1 == train_data.size()){
					System.out.println(t+1);
					System.out.println(df.format(new_weights.get(0)) + " " + df.format(new_weights.get(1)) + " "
							+ df.format(new_weights.get(2)) + " " + df.format(new_weights.get(3)) + " "
							+ df.format(new_weights.get(4)) + " " + df.format(new_weights.get(5)) + " "
							+ df.format(new_weights.get(6)) + " " + df.format(new_weights.get(7)) + " "
							+ df.format(new_weights.get(8)));
					System.out.println(df.format(E_sum));
					System.out.println(df.format(accurancy(new_weights, test_data)));
					break outerloop;
					}
					weights = new_weights;
					old_E_sum = E_sum;
				}
			}
		}

	}
	static double accurancy(ArrayList<Double> weights, ArrayList<data> test_data){
		int predict_correct = 0;
		for(data d:test_data){
			if (calculate_vc(weights, d.hw2, d.midterm, d.Apass)>=0.5){
				if(d.Apass == 1){
					predict_correct ++;
				}
			}
			else{
				if(d.Apass == 0){
					predict_correct ++;
				}
			}
		}
		return (double)predict_correct/(double)test_data.size();
		
	}
	static double calculate_vc(ArrayList<Double> weights, double x1, double x2, double y) {
		double ua = 1 * weights.get(0) + x1 * weights.get(1) + x2 * weights.get(2);
		double ub = 1 * weights.get(3) + x1 * weights.get(4) + x2 * weights.get(5);
		double va = Math.max(ua, 0);
		double vb = Math.max(ub, 0);
		double uc = 1 * weights.get(6) + va * weights.get(7) + vb * weights.get(8);
		double vc = 1 / (Math.exp(uc * (-1)) + 1);
		double E = 0.5 * (vc - y) * (vc - y);
		return vc;
	}
}

class data {
	double hw2;
	double midterm;
	double Apass;

}