package dev.dren.linkplus.assignment;

import dev.dren.linkplus.assignment.bank.Bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dev.dren.linkplus.assignment")
@EntityScan("dev.dren.linkplus.assignment")
/*public class Application {

	private static final String BASE_URL = "http://localhost:8080";
	private static Scanner scanner = new Scanner(System.in);

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Application started!");

		while (true) {
			System.out.println("\nChoose an action:");
			System.out.println("1. Create Bank");
			System.out.println("2. View Bank");
			System.out.println("3. Create Account");
			System.out.println("4. View Account");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");

			int choice = scanner.nextInt();
			switch (choice) {
				case 1:
					createBank();
					break;
				case 5:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice, please choose again.");
			}
		}

	}

	private static void createBank() {
		try {
			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter bank name: ");
			String bankName = scanner.nextLine();

			System.out.print("Enter transaction flat fee amount ($): ");
			double flatFeeAmount = scanner.nextDouble();

			System.out.print("Enter transaction percent fee value (%): ");
			double percentFeeValue = scanner.nextDouble();

			String jsonInputString = String.format(
					"{\"name\": \"%s\", \"transactionFlatFeeAmount\": %.2f, \"transactionPercentFeeValue\": %.2f}",
					bankName, flatFeeAmount, percentFeeValue
			);

			URL url = new URL("http://localhost:8080/banks");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			int responseCode = conn.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_CREATED) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				System.out.println("Bank created successfully: " + response.toString());
			} else {
				System.out.println("POST request did not work. Response Code: " + responseCode);
				System.out.println("url: " + url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}*/

public class Application {

	private static final String BASE_URL = "http://localhost:8080";
	private static Scanner scanner = new Scanner(System.in);

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Application started!");
	}
}
