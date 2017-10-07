package model;

public class Hint {
	 private int hintNum; 
	 private String text;
	 private Room belongRoom;
	 
	 //constructor
		/**
		 * @param hintNum
		 * @param text
		 * @param belongRoom
		 */
		public Hint(int hintNum, String text, Room belongRoom) {
			super();
			this.hintNum = hintNum;
			this.text = text;
			this.belongRoom = belongRoom;
		}
		
	//tostring and equals
	public String toString() {
		return "Hint [hintNum=" + hintNum + ", text=" + text + ", belongRoom=" + belongRoom + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hintNum;
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
		Hint other = (Hint) obj;
		if (hintNum != other.hintNum)
			return false;
		return true;
	}
	//getters and setters
	public int getHintNum() {
		return hintNum;
	}
	public void setHintNum(int hintNum) {
		this.hintNum = hintNum;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Room getBelongRoom() {
		return belongRoom;
	}
	public void setBelongRoom(Room belongRoom) {
		this.belongRoom = belongRoom;
	}

}
