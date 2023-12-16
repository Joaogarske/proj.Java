package program;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import Model.entities.Contract;
import Model.entities.Installment;
import Model.services.ContractService;
import Model.services.PaypalService;

public class ProgramInterf{

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner in = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato: ");
		System.out.print("Numero do contrato: ");
		int number = in.nextInt();
		System.out.print("Data do contrato (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(in.next(),fmt);
		System.out.print("Valor do contrato: ");
		Double totalValue = in.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.println("Entre com o numero de parcelas: ");
		int n = in.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		contractService.processContract(contract, n);
		
		System.out.println("Parcelas: ");
		for(Installment installment : contract.getInstallment()) {
			System.out.println(installment);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		in.close();

	}

}

