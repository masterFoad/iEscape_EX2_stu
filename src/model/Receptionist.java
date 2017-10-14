package model;

import controller.SysData;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Class Receptionist ~ represent a single Receptionist of the company,
 * inheritor of Employee
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Receptionist extends Employee implements IsUser<Receptionist>{
	// -------------------------------Class
	// Members------------------------------
	private HashMap<Integer, Subscription> Subscriptions;

	// -------------------------------Constructors------------------------------
	public Receptionist(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, Address address) {
		super(empNum, firstName, lastName, birthdate, startWorkingDate, password, address);
		this.Subscriptions = new HashMap<>();
	}

	public Receptionist(int empNum) {
		super(empNum);
	}

	// -------------------------------Getters And
	// Setters------------------------------
	public HashMap<Integer, Subscription> getSubscriptions() {
		return Subscriptions;
	}

	public void setSubscriptions(HashMap<Integer, Subscription> subscriptions) {
		Subscriptions = subscriptions;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds a subscription to the subscription's array IF it does
	 * not already exists
	 * 
	 * @param sub
	 * @return true if the subscription was added successfully, false otherwise
	 */
	public boolean addSubscription(Subscription sub) {
		// TODO complete this method
		if(sub!=null){
			
			if(!this.Subscriptions.containsKey(sub.getNumber())){
				this.Subscriptions.put(sub.getNumber(), sub);
				return true;
			}
		}return false;
	}

	/**
	 * This method deletes a subscription from the subscriptions array
	 * 

	 * @return true if the lesson was deleted successfully, false otherwise
	 */
	public boolean removeSubscription(Subscription sub) {
		// TODO complete this method
		if(sub!=null){
			if(!this.Subscriptions.containsKey(sub.getNumber())){
				this.Subscriptions.remove(sub.getNumber(), sub);
				return true;
			}
		}return false;
	}

	/**
	 * This method counts the number of subscriptions the receptionist handled
	 * in January of this year. only the subscriptions with the relevant dates.
	 * 
	 * @return numOfAssignments at january of this year
	 */
	@SuppressWarnings("deprecation")
	public int getNumberOfThisYearJanuaryAssignments() {
		int numOfAssignments = 0;
		for (Subscription sub : Subscriptions.values())
			if (sub.getStartDate().getMonth() == 0 && sub.getStartDate().getYear() + 1900 == Year.now().getValue())
				numOfAssignments++;
		return numOfAssignments;
	}

	@Override
	public Receptionist validateUserAndPass() {
		ArrayList<Receptionist> Receptionists = SysData.getInstance().getReceptionists();


		if(Receptionists!=null){
			if(Receptionists.contains(this) && Receptionists.get(Receptionists.indexOf(this)).getPassword().equals(this.getPassword())){
				return Receptionists.get(Receptionists.indexOf(this));
			}
		}
		return null;
	}
}
