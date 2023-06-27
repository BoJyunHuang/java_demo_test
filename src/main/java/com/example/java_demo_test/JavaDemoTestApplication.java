package com.example.java_demo_test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaDemoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaDemoTestApplication.class, args);
		
		        String[] fields = {"title", "detail", "price", "build_date"};
		        String[] titles = {"vip_income", "vehicle_cost", "maintenance_cost", "rent_income"};
		        String[] detailsVipIncome = {"vip"};
		        String[] detailsVehicleCost = {"bike", "scooter", "motorcycle", "heavy motorcycle", "sedan", "ven", "suv"};
		        String[] detailsMaintenanceCost = {"A01", "A02", "A03", "B01", "B02", "B03", "C01", "C02", "C03", "C04", "C05"};
		        String[] detailsRentIncome = {"bike", "scooter", "motorcycle", "heavy motorcycle", "sedan", "ven", "suv"};

		        Random random = new Random();

		        try (PrintWriter writer = new PrintWriter(new FileWriter("data.csv"))) {
		            // �g�J���
		            writer.println(String.join(",", fields));

		            // �ͦ����
		            for (int i = 0; i < 300; i++) {
		                String title = titles[random.nextInt(titles.length)];
		                String detail;
		                if (title.equals("vip_income")) {
		                    detail = detailsVipIncome[0];
		                } else if (title.equals("vehicle_cost")) {
		                    detail = detailsVehicleCost[random.nextInt(detailsVehicleCost.length)];
		                } else if (title.equals("maintenance_cost")) {
		                    detail = detailsMaintenanceCost[random.nextInt(detailsMaintenanceCost.length)];
		                } else {
		                    detail = detailsRentIncome[random.nextInt(detailsRentIncome.length)];
		                }
		                int price = random.nextInt(991000) + 1000; // �H���ͦ�����]10��1000��������ơ^
		                String buildDate = generateRandomDate("2023-01-01", "2023-05-31", random);

		                // �g�J���
		                writer.println(title + "," + detail + "," + price + "," + buildDate);
		            }

		            System.out.println("�w�ͦ�300����ơA�äw�x�s��data.csv�ɮפ��C");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }}

	private static String generateRandomDate(String startDate, String endDate, Random random) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(startDate);
			Date end = format.parse(endDate);

			long startTime = start.getTime();
			long endTime = end.getTime();
			long randomTime = startTime + (long) (random.nextDouble() * (endTime - startTime));

			return format.format(new Date(randomTime));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}


