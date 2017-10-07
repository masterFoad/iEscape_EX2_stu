package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import model.Address;
import model.Branch;
import model.Customer;
import model.Hint;
import model.Instructor;
import model.Receptionist;
import model.Room;
import model.RoomRun;
import model.Subscription;
import utils.Constants;
import utils.E_Cities;
import utils.E_Equipment;
import utils.E_Levels;
import utils.E_Periods;
import utils.E_Rooms;

/**
 * This SysData object ~ represents the class system
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class SysData {
	// -------------------------------Class
	// Members------------------------------
	private ArrayList<Instructor> instructors;
	private ArrayList<Receptionist> receptionists;
	private ArrayList<Branch> branches;
	private ArrayList<Customer> customers;
	private ArrayList<RoomRun> roomRuns;

	// -------------------------------Constructors------------------------------
	public SysData() {
		instructors = new ArrayList<Instructor>();
		receptionists = new ArrayList<Receptionist>();
		branches = new ArrayList<Branch>();
		customers = new ArrayList<Customer>();
		roomRuns = new ArrayList<RoomRun>();
	}

	// -----------------------------------------Getters--------------------------------------
	public ArrayList<Branch> getBranches() {
		return branches;
	}

	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}

	public ArrayList<Receptionist> getReceptionists() {
		return receptionists;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public ArrayList<RoomRun> getRoomRuns() {
		return roomRuns;
	}

	// -------------------------------Add && Remove
	// Methods------------------------------
	/**
	 * This method adds a new branch to our company IFF the branch doesn't
	 * already exist and the details are valid.
	 * 
	 * @param branchNumber
	 * @param branchName
	 * @param city
	 * @param street
	 * @param houseNumber
	 * @param phoneNumber
	 * @return true if the branch was added successfully, false otherwise
	 */
	public boolean addBranch(int branchNumber, String branchName, E_Cities city, String country, String street,
			int houseNumber, String[] phoneNumber) {
		if (branchName != null && branchNumber > 0 && city != null && street != null && houseNumber > 0
				&& phoneNumber != null && country != null) {
			Branch branchToAdd = new Branch(branchNumber);
			if (!branches.contains(branchToAdd)) {
				Address branchAddress = new Address(country, city, street, houseNumber, phoneNumber);
				branchToAdd = new Branch(branchNumber, branchName, branchAddress);
				return branches.add(branchToAdd);
			}
		}
		return false;
	} // ~ END OF addBranch

	/**
	 * Creates and adds a new instructor into the relevant data-structure
	 * 
	 * @param instructor
	 * @return true IF the instructor was added successfully, false otherwise
	 */
	public boolean addInstructor(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, int level, Address address, E_Rooms[] types) {
		if (empNum > 0 && !firstName.equals("") && !lastName.equals("") && birthdate != null && startWorkingDate != null
				&& !password.equals("") && level > 0 && address != null && types != null) {
			Instructor instructor = new Instructor(empNum, firstName, lastName, birthdate, startWorkingDate, password,
					level, address, types);
			if (instructor != null && !instructors.contains(instructor)) {
				return instructors.add(instructor);
			}
		}
		return false;
	} // ~ END OF addInstructor

	/**
	 * Creates and adds a new receptionist into the relevant data-structure
	 *
	 * @param empNum
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param startWorkingDate
	 * @param password
	 * @param address
	 * @return true IF the receptionist was added successfully, false otherwise
	 */
	public boolean addReceptionist(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, Address address) {
		if (empNum > 0 && !firstName.equals("") && !lastName.equals("") && birthdate != null && startWorkingDate != null
				&& !password.equals("") && address != null) {
			Receptionist receptionist = new Receptionist(empNum, firstName, lastName, birthdate, startWorkingDate,
					password, address);
			if (receptionist != null && !receptionists.contains(receptionist)) {
				return receptionists.add(receptionist);
			}
		}
		return false;
	} // ~ END OF addReceptionist

	/**
	 * Creates and adds a new customer into the relevant data-structure. ID
	 * number length needs to be as it's represented in Constants class and
	 * contains only digits.
	 * 
	 * @see Constants #ID_NUMBER_SIZE
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param password
	 * @param email
	 * @param customerAddress
	 * @return true if the customer was added successfully, false otherwise
	 */
	public boolean addCustomer(String id, String firstName, String lastName, Date birthDate, String password,
			E_Levels level, URL email, Address customerAddress) {
		// Check validity first
		if (id != null && firstName != null && lastName != null && birthDate != null && password != null
				&& level != null && email != null && customerAddress != null) {
			// Creating a new customer with his full constructor
			Customer customer = new Customer(id, firstName, lastName, birthDate, password, level, email,
					customerAddress);
			// Check if ID equals to "0" - incorrect ID
			if (customer.getId().equals("0"))
				return false;
			if (!customers.contains(customer)) {
				return customers.add(customer); // Add this customer;
			}
		}
		return false;
	}// ~ END OF addCustomer

	/**
	 * Creates a new subscription, and add it to the relevant customer and
	 * receptionist.
	 * 
	 * @param subNumber
	 * @param custId
	 * @param period
	 * @param startDate
	 * @return true if the subscription was added to the customer, false
	 *         otherwise
	 */
	public boolean addSubToCustomer(int subNumber, String custId, int receptNumber, E_Periods period, Date startDate) {
		// Check validity first
		// TODO complete this method
		if (subNumber<1 || Customer.checkId(custId).equals("0") || receptNumber<1  || period==null || startDate==null)
			return false;
			
		Customer customer = null;
		for(Customer c : customers)
			if (c.getId().equals(custId)){
				customer=c;
				break;
		}
		if(customer==null) return false;
		
		Receptionist rece = null;
		for (Receptionist r :receptionists)
			if (r.getEmployeeNumber()==receptNumber){
				rece=r;
				break;
         }
		if(rece==null) return false;
		if(rece.getWorkBranch()==null) return false;
        Subscription s = new Subscription(subNumber, customer, rece, period, startDate);
        if(customer.addSubscription(s)){
        	if(rece.addSubscription(s)){
        		return true;
        	}// if get here then subs didn't added to the recep
        	customer.removeSubscription(s);
        }
		return false;
	} // ~ END OF addSubToCustomer

	/**
	 * This method connects an instructor to a branch IF the branch and the
	 * instructor exists.
	 * 
	 * @param instructorNumber
	 * @param branchNumber
	 * @return true if the connection was added successfully, false otherwise
	 */
	public boolean connectInstructorToBranch(int instructorNumber, int branchNumber) {
		// Check validity first
		if (instructorNumber > 0 && branchNumber > 0) {
			Instructor instructor = new Instructor(instructorNumber);
			Branch branch = new Branch(branchNumber);
			if (instructors.contains(instructor) && branches.contains(branch)) {
				// Get the branch
				branch = branches.get(branches.indexOf(branch));
				// Get the instructor
				instructor = instructors.get(instructors.indexOf(instructor));
				if (branch.addInstructor(instructor)) {
					instructor.setWorkBranch(branch);
					return true;
				}
			}
		}
		return false;
	}// ~ END OF connectInstructorToBranch

	/**
	 * This method connects a Receptionist to a branch IF the branch and the
	 * Receptionist exists.
	 * 
	 * @param receptionistNumber
	 * @param branchNumber
	 * @return true if the connection was added successfully, false otherwise
	 */
	public boolean connectReceptionistToBranch(int receptionistNumber, int branchNumber) {
		// TODO complete this method
		// Check validity first
		if(this.branches.contains(new Branch(branchNumber))&& this.receptionists.contains(new Receptionist(receptionistNumber))){
			Branch b = this.branches.get(this.branches.indexOf(new Branch(branchNumber)));
			Receptionist r = this.receptionists.get(this.receptionists.indexOf(new Receptionist(receptionistNumber)));
			if(b.addReceptionist(r)){
				r.setWorkBranch(b);
				return true;
			}
		}
		return false;
		}

	/**
	 * this method adds a room to a branch IF the branch already exist
	 * 
	 * @param roomNum
	 * @param maxNumOfTrainers
	 * @param roomType
	 * @param branchNum
	 * @return true if the room was added to the branch, false otherwise
	 */
	public boolean addRoomToBranch(int roomNum, String name, int maxNumOfParticipants, int minNumOfParticipants,
			int timeLinit, E_Levels level, E_Rooms roomType, int branchNum) {
		// Check validity first
		if(roomNum<1 || name==null || maxNumOfParticipants<1 || minNumOfParticipants<1
				||timeLinit<1 || level==null || roomType==null || branchNum<1) return false;
		// TODO complete this method
		Branch b=new Branch(branchNum);
		if(!this.branches.contains(b)) return false;
		
		// finding the branch 
		for(Branch branch:branches){
			if(branch.getBranchNumber()==branchNum){
				b=branch;
				break;
			}
		}
		
		//room constructor
		Room room=new Room(roomNum, name, maxNumOfParticipants, minNumOfParticipants, timeLinit, level, roomType, b);
		// adding the room to the branch
		if(b.addRoom(room))
		return true;
		return false;
		
	}// ~ END OF addRoomToBranch
	
	/**
	 * This method adds a hint to a room IF the branch & room already exist
	 * @param branchNum
	 * @param roomNum
	 * @param hintNum
	 * @param text
	 * @return true if the hint was added to the room, false otherwise
	 */
	public boolean addHintToRoom(int branchNum, int roomNum, int hintNum, String text) {
		// TODO complete this method
		if(branchNum<1 || roomNum<1 || hintNum<1 ||text==null) return false;
		for(Branch b : branches){
			for (Room r: b.getRooms().values()){
				if(b.getBranchNumber()==branchNum && r.getRoomNum()==roomNum){
					Hint h = new Hint(hintNum, text, r);
					if(r.getHints().contains(h)) return false;
					r.addHint(h);
					return true;
				}
			}
		} return false;
	}

	/**
	 * This method add a new roomRun to SysData Hint- think of all the things
	 * that are related to a roomRun or should store the roomRun, and don't
	 * forget to rollBack if needed
	 * 
	 * @param roomRunNum
	 *            \ * @param dateTime
	 * @param level
	 * @param instructorNum
	 * @param branchNum
	 * @param roomNum
	 * @return true if a roomRun was added, false otherwise
	 */
	public boolean addRoomRun(int roomRunNum, Date dateTime, int duration, int instructorNum, int branchNum,
			int roomNum) {
		// Check validity first
		if(roomRunNum<1 || dateTime==null || duration<1 ||instructorNum<1 || branchNum<1
				|| roomNum<1) return false;
		// TODO complete this method
		// find the relevant room 
	if(this.branches.contains(new Branch(branchNum))){
		Branch b = this.branches.get(this.branches.indexOf(new Branch(branchNum)));
		if(b.getRooms().containsKey(roomNum)){
			Room r = b.getRooms().get(roomNum);
			if(this.instructors.contains(new Instructor(instructorNum))){
			Instructor	ins = this.instructors.get(this.instructors.indexOf(new Instructor(instructorNum)));
			
			RoomRun r1 = new RoomRun(roomRunNum, dateTime, duration, ins, r);
			r1.setLevel(r.getLevel());
			if(r.addRoomRun(r1)){
				if(ins.addRoomRun(r1)){
					return this.roomRuns.add(r1);
				}else{
					r.deleteRoomRun(r1);
				}
			}
			}
		}
		
	}return false;
	}

	/**
	 * This method adds an equipment to a specific roomRun if this equipment
	 * is suitable for this room type.
	 * @param roomRunNum
	 * @param equipment
	 * @return true if an equipment was added, false otherwise
	 */
	public boolean addEquipmentToRoomRun(int roomRunNum, E_Equipment equipment) {
		// TODO complete this method
       if(roomRunNum<1 || equipment==null) return false;
       // find the relevant roomrun
       RoomRun roomrun=null;
       for(Branch b :branches){
    	   for(Room r : b.getRooms().values()){
    		   for(RoomRun rr: r.getRoomRuns().values()){
    			   if (rr.getRoomRunNum()==roomRunNum){
    				   // check if this equipment is suitable for this room type
    				   if(!equipment.getRoomTypes().contains(r.getRoomType())) return false;
    				   // and of check;
    				   roomrun=rr;
    				   break;
    			   }
    		   }
    	   }
       }
       if(roomrun!=null){
        if(roomrun.addEquipment(equipment)){
        return true;
        }
       }return false;
	}
	
	/**
	 * This method removes an equipment from a specific roomRun.
	 * @param roomRunNum
	 * @param equipment
	 * @return true if succeeded, false otherwise
	 */
	public boolean removeEquipmentFromRoomRun(int roomRunNum, E_Equipment equipment) {
		// TODO complete this method
		if(roomRunNum<1 || equipment==null) return false;
		 // find the relevant roomrun
	       RoomRun roomrun=null;
	       for(Branch b :branches){
	    	   for(Room r : b.getRooms().values()){
	    		   for(RoomRun rr: r.getRoomRuns().values()){
	    			   if (rr.getRoomRunNum()==roomRunNum){
	    				   roomrun=rr;
	    				   break;
	    			   }
	    		   }
	    	   }
	       }if (roomrun==null) return false;
	       if(!roomrun.getEquipments().contains(equipment)) return false;
	       roomrun.getEquipments().remove(equipment);
	       return true;
		
	}
	
	/**
	 * This method adds a customer to a specific roomRun if his subscription
	 * fits, he has no other roomRuns at the time, and there is a free space in
	 * the class Hint: if needed, don't forget to rollback
	 * 
	 * @param custNum
	 * @param roomRunNum
	 * @return true if the customer is registered to the roomRun, false
	 *         otherwise
	 */
	public boolean addCustomerToRoomRun(String custNum, int roomRunNum) {
		// Check validity first
		if (custNum==null ||roomRunNum<1) return false;
		// TODO complete this method
         if(this.roomRuns.contains(new RoomRun(roomRunNum))&& this.customers.contains(new Customer(custNum))){
        	 
         RoomRun rr = this.roomRuns.get(this.roomRuns.indexOf(new RoomRun(roomRunNum)));
         Customer c = this.customers.get(this.customers.indexOf(new Customer(custNum)));
		if(rr.addParticipant(c)){
			if(c.addRoomRun(rr)){
				return true;
			}else{
				rr.removeParticipant(c);
			}}}return false;
		}
		
		// check if there is a free space in the class
	//	if(!(rr.getParticipated().size() < rr.getRoom().getMaxNumOfParticipants())) return false;
		
//		// check if the subscription fits
//		Date today = new Date();
//		for(Subscription s :c.getSubs().values()){
//			if(s==null) return false;
//			for(RoomRun rr2:s.getRoomRuns().values()){
//				if(rr2==null) return false;
//				if(s.getLastDay().after(today) && s.getRoomRuns().containsKey(rr.getRoomRunNum())){
//					check++; break;
//                    	
//                      }
//				}
//			}
//		//check if the customer has no other roomRuns at the time
//          for (Subscription S: c.getSubs().values()){
//        	  for(RoomRun RR : S.getRoomRuns().values()){
//        		  if(!(rr.getStartDateTime().before(RR.getFinishDateTime())&& rr.getFinishDateTime().after(RR.getFinishDateTime()))){
//        			  if(!(rr.getStartDateTime().before(RR.getStartDateTime())&& rr.getFinishDateTime().after(RR.getStartDateTime()))){
//        				  if(!(rr.getStartDateTime().before(RR.getStartDateTime())&& rr.getFinishDateTime().after(RR.getFinishDateTime()))){
//        			        check++;
//        				  }
//        		  }
//        	  }
//          }}
//		
//		if(check==2) {
//		if(	c.addRoomRun(rr)) return true;
//		}
//		return false;
//		}

	
	/**
	 * Gives a hint to specific roomRun
	 * @param roomRunNum
	 * @param hintNum
	 * @return true if succeeded, false otherwise
	 */
	public boolean giveAHint(int roomRunNum, int hintNum) {
		// TODO complete this method
		if(roomRunNum<1 || hintNum<1) return false;
		// find the roomrun
		RoomRun rr =null;
		for(RoomRun rr2 : roomRuns){
			if(rr2.getRoomRunNum()==roomRunNum) {
				rr=rr2;
				break;
			}
		}
		//find the hint 
		Hint hint = null;
		for(Branch b : branches){
			if(b==null) return false;
			for(Room room : b.getRooms().values()){
				if(room==null) return false;
				for (Hint h : room.getHints()){
					if(h==null) return false;
					if(h.getHintNum()==hintNum){
						hint=h;
					}
				}
			}
		}
		if(rr!=null){
		if(rr.addHint(hint)){
		return true;
		}
		}return false;
	}

	/**
	 * This method change the address of existing customer IFF the customer
	 * already exist and the Address details are valid.
	 * 
	 * @param id
	 * @param country
	 * @param city
	 * @param street
	 * @param houseNumber
	 * @param phonenumbers
	 * @return true if succeeded, false otherwise
	 */
	public boolean changeCustomerAddress(String id, String country, E_Cities city, String street, int houseNumber,
			String[] phonenumbers) {
		// Check validity first
		if (id.length() == Constants.ID_NUMBER_SIZE && country != null && city != null && street != null
				&& houseNumber > 0 && phonenumbers != null) {
			Customer customer = new Customer(id);
			if (customers.contains(customer)) {
				customer = customers.get(customers.indexOf(customer));
				customer.setCustomerAddress(new Address(country, city, street, houseNumber, phonenumbers));
				return true;
			}
		}
		return false;
	} // ~ END OF changeCustomerAddress

	/**
	 * This method cancels a subscription from the system using the subNumber
	 * (Primary Key).
	 * 
	 * @param subNumber
	 * @return true if subscription was canceled, false otherwise
	 */
	public boolean removeSubscription(int subNumber) {
for(Customer cus : this.customers){
	if(cus.getSubs().containsKey(subNumber)){
		if(cus.removeSubscription(cus.getSubs().get(subNumber))){
			return true;
	}
	}
}
				return false;
	
		
		
		
		// TODO complete this method
	}// ~ END OF removeSubscription
		// -------------------------------Queries------------------------------
		// ===================================================
		// HW_2_Queries
		// ===================================================

	/**
	 * This method returns all roomRuns of the most active customer. Most active
	 * customer is the customer with the most PARTICIPATED roomRuns , A roomRuns
	 * will be counted as participated if its date has past already
	 * 
	 * @return participatedRoomRuns if found, empty list otherwise
	 */
	public List<RoomRun> getAllParticipatedRoomRunsOfMostActiveCustomer() {
		// TODO complete this method
		//finding the most active customer
		int max=0;
		Customer customer = null;
		for(Customer c : this.customers){
			if(c.getParticipatedRoomRuns().size()>=max){
				max=c.getParticipatedRoomRuns().size();
				customer=c;
			}
		}
		return  customer.getParticipatedRoomRuns();
	}

	/**
	 * This method finds the receptionisnt who sold the biggest amount of
	 * subscriptions this january.
	 * 
	 * @return topJanuaryReceptionist if found, null otherwise
	 */
	public Receptionist getTopJanuaryReceptionist() {
		// TODO complete this method
		Receptionist R =null;
		int max=0;
		for(Receptionist r : this.receptionists){
			for(Subscription s :r.getSubscriptions().values()) {
				int count=0;
				if(s.getStartDate().getMonth()==0){
					count++;
				}
				if(count>max){
					max= count;
					R=r;
				}
			}
		}
		return R;
	} // ~END OF getTopJanuaryReceptionist

	/**
	 * This query returns the most popular room type. Most popular room type is
	 * the type with the highest number of registered roomRuns.
	 * 
	 * @return mostPopularRoomType if found, null otherwise
	 */
	public E_Rooms getTheMostPopularRoomType() {
		// TODO complete this methodr : null
		int max=0;
		E_Rooms e =null;
		for(E_Rooms r : E_Rooms.values()){
			int count=0;
			for(RoomRun ru : this.roomRuns){
				if(ru.getRoom().getRoomType().equals(r)){
					count++;
				}
			
			}if(count>max){
				max=count;
				e=r;
			}
			
		}return e;
		
		
	} // ~END OF getTheMostPopularRoomType

	/**
	 * This method finds all of the super senior instructors of this month. An
	 * instructor is considered a super senior instructor if he started working
	 * over 15 years ago and guided at least 2 roomRuns this month
	 * 
	 * @return an array of super senior instructors if found, null otherwise
	 */
	@SuppressWarnings("deprecation")
	public Instructor[] getAllSuperSeniorInstructors() {
		// TODO complete this method
		Instructor[] inst = new Instructor[instructors.size()];
		int j=0;
		Date today=new Date();
		for(Instructor i : instructors){
		    if (i.getEmployeeSeniority()>15){
		    	int count=0;
		    	for(RoomRun rr:i.getRoomRuns().values()){
		    		if(rr.getStartDateTime().getMonth()==today.getMonth()) count++;
		    	}
		    if(count>=2){
		    	inst[j]=i;
		    	j++;
		    }
		    }
		}
		    if(inst.length==0) return null;
		    return inst;
		
		
	} // ~END OF getAllSuperSeniorInstructors

	/**
	 * This query finds the most active day, the most active day is the day 
	 * with the highest number of roomRuns
	 * 
	 * @return the most active day, null otherwise
	 */
	@SuppressWarnings("deprecation")
	public Date getTheMostActiveDay() {
		Date  date1= new Date();
		HashSet< Date> d = new HashSet<Date>();
		for(RoomRun r : this.roomRuns){
			Date  date  = new Date(r.getStartDateTime().getYear(), 
					r.getStartDateTime().getMonth(), r.getStartDateTime().getDate());
			d.add(date);
		}
		int max=0;
		Date maxdat=null;
		for(Date temp : d){
			int co=0;
			for(RoomRun r : this.roomRuns){
				Date dater = new Date(r.getStartDateTime().getYear(),
						r.getStartDateTime().getMonth(),
						r.getStartDateTime().getDate());
				if(dater.equals(temp)){
					co++;
				}
			}if(co>max){
				max=co;
				maxdat=temp;
			}
		}
		return maxdat ;
	} // ~END OF getTheMostActiveDay
	
	/**
	 * This query finds all the roomRuns without given hints
	 * 
	 * @return the array of roomRuns, null or empty otherwise
	 */
	public ArrayList<RoomRun> getAllRoomRunsWithoutHints() {
		// TODO complete this method
		
		//creating the array to return
		 ArrayList<RoomRun> roomRunsToReturn=new ArrayList<RoomRun>();
		 
		 for(RoomRun rr : this.roomRuns){
			 if (rr.getTakenHints().size()==0){
			     if(rr!=null){
				 roomRunsToReturn.add(rr);
			 }
			 }
		 }
       if(roomRunsToReturn.size()==0) return null;
           return roomRunsToReturn;
		
	} // ~END OF getTheMostActiveDay
		
	/**
	 * This query returns the top equipment, top equipment is the type of equipment 
	 * that is ordered the most times, ordered means used in roomruns
	 * 
	 * @return the most ordered equipment, null otherwise
	 */
	public E_Equipment getTopEquipment() {
		// TODO complete this method
		HashMap<E_Equipment, Integer> map= new HashMap<>();
		for(RoomRun rr: roomRuns){
			for(E_Equipment eq: rr.getEquipments()){
				if(map.containsKey(eq))
					map.put(eq, map.get(eq)+1);
				else
					map.put(eq, 1);
			}
		}
		int max=0;
		E_Equipment e =null;
		for(java.util.Map.Entry<E_Equipment, Integer> a : map.entrySet()){ 
			// try for(E_Equipment eq: map.values())
			if(a.getValue()>max){
				max=a.getValue();
				e=a.getKey();
			}
		}
		return e;
		
	} // ~END OF getTopEquipment
	/**
	 * this query returns all customers who participated in roomruns in
	 * more than 1 city
	 * values must be sorted by key
	 * 
	 * @return the list of customers, null otherwise
	 */
	public ArrayList<Customer> getAllCustomersAttendingMoreThan1City() {
		// TODO complete this method
		// creating an array for customers in ordet to return it 
		ArrayList<Customer> array = new ArrayList<Customer>();
		for(Customer c : this.customers){
			HashSet<E_Cities> cityforcustomer =new HashSet<E_Cities>() ;
			for(RoomRun r : c.getParticipatedRoomRuns()){
				
				cityforcustomer.add(r.getRoom().getBranch().getBranchAddress().getCity());
			}if(cityforcustomer.size()>1){
				array.add(c);
			}
			
		}
		
		return array;
	} // ~END OF getAllCustomersAttendingMoreThan1City
	
	/**
	 * this query finds and returns all popular branches A popular branch is a
	 * branch with more then 2 hours of total roomruns duration this month
	 * values must be sorted by key
	 * 
	 * @return a set with all popular branches
	 */
	@SuppressWarnings("deprecation")
	public Set<Branch> getPopularBranches() {
		// TODO complete this method
	Set<Branch> bramchTemp  = new TreeSet<Branch>( new Comparator<Branch>() {
			
			@Override
			public int compare(Branch o1, Branch o2) {
				// TODO Auto-generated method stub
				if(o1.getBranchNumber()>o2.getBranchNumber()){
					return 1;
				}if(o1.getBranchNumber()<o2.getBranchNumber()){
					return -1;
				}else{
					return 0;
				}
			}
		} );
		Date dateTogetMonth = new Date();
		for(Branch branch : this.branches){
			int count=0;
			for(RoomRun r : this.roomRuns){
				if(r.getStartDateTime().getMonth()==dateTogetMonth.getMonth()&&
						r.getRoom().getBranch().equals(branch)){
					count+=r.getDuration();
				}}if(count>=60){
				bramchTemp.add(branch);
			}
		}
	
		
		 return bramchTemp;
		
	} // ~END OF getPopularBranches
	
	
	/**
	 * this query returns a map with all branches that have potential
	 * customers, and their potential customer A potential customer for branch
	 * is a customer that has no valid subscription and lives in the same city
	 * as the branch address
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Branch, ArrayList<Customer>> getPotentialCustomersPerBranch() {
		// TODO complete this method
		Map<Branch, ArrayList<Customer>> m = new HashMap<Branch, ArrayList<Customer>>();
		for(Branch  b : this.branches){
			ArrayList<Customer> a = new ArrayList<Customer>();
			for(Customer c : this.customers){
				if(c.getCustomerAddress().getCity().equals(b.getBranchAddress().getCity())&& c.getSubs().size()==0){
					a.add(c);
				}
			}
			m.put(b, a);
			
		}
		return m;
	} // ~END OF getPotentialCustomersPerBranch
}// ~ END OF Class SysData
