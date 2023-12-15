package Model.services;

import java.time.LocalDate;

import Model.entities.Contract;
import Model.entities.Installment;

public class ContractService {
	private Contract contract;
	private OnlinePaymentService onlinePaymentService;  
	
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
	
		this.onlinePaymentService = onlinePaymentService;
	}



	public void processContract(Contract contract, Integer months) {
		
		double basicQuota = contract.getTotalValue() / months;
		
		for(int i = 1; i <=months ;i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			
			double interest = onlinePaymentService.interest(basicQuota, i);
			
			double fee = onlinePaymentService.paymentFee(basicQuota + interest);
			
			double quota = basicQuota + interest + fee;
			
			contract.getInstallment().add(new Installment(dueDate, quota));
			
			
			
			
			
		}
		 
	}
	
	
}
