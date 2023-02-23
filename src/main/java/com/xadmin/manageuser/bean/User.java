package com.xadmin.manageuser.bean;

public class User {
	private int id;
	private String name;
        private String number;

    // constructor without id field
    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }
	
    // constructor with all instance variables.
    public User(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
	
	// creating getter and setter for id and name,number fields.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

    public void setNumber(String number) {
        this.number = number;
    }
}
