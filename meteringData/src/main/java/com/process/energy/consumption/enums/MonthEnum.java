package com.process.energy.consumption.enums;

/**MonthEnum is used to map all the month to specific index position
 * 
 * @author GAGAN
 *
 */
public enum MonthEnum {
	
	JAN("JAN",1),
	FEB("FEB",2),
	MAR("MAR",3),
	APR("APR",4),
	MAY("MAY",5),
	JUN("JUN",6),
	JUL("JUL",7),
	AUG("AUG",8),
	SEP("SEP",9),
	OCT("OCT",10),
	NOV("NOV",11),
	DEC("DEC",12);
	

	private String value;
	
	private int index;

	MonthEnum(String value,int index) {
		this.value = value;
		this.index = index;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static MonthEnum fromValue(String text) {
		for (MonthEnum b : MonthEnum.values()) {
			if (b.value.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


}
