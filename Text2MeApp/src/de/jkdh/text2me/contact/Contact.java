package de.jkdh.text2me.contact;

public class Contact {
	private String name;
	private String number;
	private String status;
	private String numbertype;
	private int _phoneID;
	private int _id;

	public Contact(int _id, int _phoneID, String name, String number,
			String status, String numtype) {
		super();
		this.name = name;
		this.number = number;
		this.setStatus(status);
		this.setNumbertype(numtype);
		this._phoneID = _phoneID;
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setPhoneID(int ID) {
		this._phoneID = ID;
	}

	public int getPhoneID() {
		return this._phoneID;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumbertype() {
		return numbertype;
	}

	public void setNumbertype(String numbertype) {
		this.numbertype = numbertype;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}
}
