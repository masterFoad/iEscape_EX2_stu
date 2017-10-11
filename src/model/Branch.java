package model;

import java.util.HashMap;



/**
 * Class Branch ~ represent a single branch of the company
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Branch implements Addressable{
	// -------------------------------Class Members------------------------------
	private int branchNumber;
	private String branchName;
	private Address branchAddress;
	private HashMap<Integer, Receptionist> receptionists;
	private HashMap<Integer, Instructor> instructors;
	private HashMap<Integer, Room> rooms;

	// -------------------------------Constructors------------------------------

	public Branch(){}

	public Branch(int branchNumber, String branchName, Address branchAddress) {
		this.branchNumber = branchNumber;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
		this.receptionists = new HashMap<>();
		this.instructors = new HashMap<>();
		this.rooms = new HashMap<>();
	}

	public Branch(int branchNumber) {
		this.branchNumber = branchNumber;
	}

	// -------------------------------Getters And
	// Setters------------------------------
	public int getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(int branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Address getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(Address branchAddress) {
		this.branchAddress = branchAddress;
	}

	public HashMap<Integer, Receptionist> getReceptionists() {
		return receptionists;
	}

	public void setReceptionists(HashMap<Integer, Receptionist> receptionists) {
		this.receptionists = receptionists;
	}

	public HashMap<Integer, Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(HashMap<Integer, Instructor> instructors) {
		this.instructors = instructors;
	}

	public HashMap<Integer, Room> getRooms() {
		return rooms;
	}

	public void setRooms(HashMap<Integer, Room> rooms) {
		this.rooms = rooms;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds a new instructor to the instructors array IF the given
	 * instructor doesn't already exists in the Branch.
	 * 
	 * @param instructor
	 * @return true if the instructor was added successfully, false otherwise
	 */
	public boolean addInstructor(Instructor instructor) {
		// TODO complete this method
		if(instructor!=null){
		if(!this.instructors.containsKey(instructor.getEmployeeNumber())){
			this.instructors.put(instructor.getEmployeeNumber(), instructor);
			return true;
		}
		}return false;
	}

	/**
	 * This method removes a given instructor from the instructors array.
	 * 
	 * @param instructor
	 * @return true if the instructor was removed successfully, false otherwise
	 */
	public boolean removeInstructor(Instructor instructor) {
		// TODO complete this method
		if(instructor!=null){
			if(this.instructors.containsKey(instructor.getEmployeeNumber())){
				this.instructors.remove(instructor.getEmployeeNumber(), instructor);
				return true;
		}
		}return false;
	}

	/**
	 * This method adds a new receptionist to the receptionists array IF the
	 * given receptionist doesn't already exists in the Branch.
	 * 
	 * @param recep
	 * @return true if the receptionist was added successfully, false otherwise
	 */
	public boolean addReceptionist(Receptionist recep) {
		// TODO complete this method
		if(recep!=null){
			if(!this.receptionists.containsKey(recep.getEmployeeNumber())){
				this.receptionists.put(recep.getEmployeeNumber(), recep);
				return true;
		}
		}return false;
	
	}

	/**
	 * This method removes a given receptionist from the receptionist array.
	 * 
	 * @param recep
	 * @return true if the receptionist was removed successfully, false
	 *         otherwise
	 */
	public boolean removeReceptionist(Receptionist recep) {
		// TODO complete this method
		if(recep!=null){
			if(!this.receptionists.containsKey(recep.getEmployeeNumber())){
				this.receptionists.remove(recep.getEmployeeNumber(), recep);
				return true;
		}
		}return false;
	
	}

	/**
	 * This method adds a new room to the rooms array IF the given room doesn't
	 * already exists in the Branch.
	 * 
	 * @param room
	 * @return true if the room was added successfully, false otherwise
	 */
	public boolean addRoom(Room room) {
		// TODO complete this method
       if(room!=null){
	        if(!this.rooms.containsKey(room.getRoomNum())){
	        	this.rooms.put(room.getRoomNum(), room);
	        	return true;
	        }
        }return false;
	}

	/**
	 * This method removes a given room from the rooms array.
	 * 
	 * @param room
	 * @return true if the room was removed successfully, false otherwise
	 */
	public boolean removeRoom(Room room) {
		// TODO complete this method
		 if(room!=null){
		        if(this.rooms.containsKey(room.getRoomNum())){
		        	this.rooms.remove(room.getRoomNum(), room);
		        	return true;
		        }
	        }return false;
	}

	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branchNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (branchNumber != other.branchNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Branch [branchNumber=" + branchNumber + ", branchName=" + branchName + ", branchAddress="
				+ branchAddress + ", receptionists=" + receptionists + ", instructors=" + instructors + ", rooms="
				+ rooms + "]";
	}

	@Override
	public Address getTheAddress() {
		return this.branchAddress;
	}

	@Override
	public void setTheAddress(Address address) {
			this.branchAddress=address;
	}
}
