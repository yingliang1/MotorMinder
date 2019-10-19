package models;

public class Car
{
    private String vin;
    private String year;
    private String make;
    private String model;
    private String nickName;
    private String username;
    private int mileage;
    private int active;

    public Car(String year, String make, String model, String vin, String nickName, String username, int mileage,
	    int active)
    {
	this.vin = vin;
	this.year = year;
	this.make = make;
	this.model = model;
	this.nickName = nickName;
	this.username = username;
	this.mileage = mileage;
	this.active = active;

    }
    
    public Car() {
      //no arg constructor
    }

    public String getVin()
    {
	return vin;
    }

    public void setVin(String vin)
    {
	this.vin = vin;
    }

    public String getYear()
    {
	return year;
    }

    public void setYear(String year)
    {
	this.year = year;
    }

    public String getMake()
    {
	return make;
    }

    public void setMake(String make)
    {
	this.make = make;
    }

    public String getModel()
    {
	return model;
    }

    public void setModel(String model)
    {
	this.model = model;
    }

    public String getNickName()
    {
	return nickName;
    }

    public void setNickName(String nickName)
    {
	this.nickName = nickName;
    }

    public String getUsername()
    {
	return username;
    }

    public int getActive()
    {
	return active;
    }

    public void setActive(int active)
    {
	this.active = active;
    }

    public int getMileage()
    {
	return mileage;
    }

    public void setMileage(int mileage)
    {
	this.mileage = mileage;
    }

    public void setUsername(String username)
    {
	this.username = username;
    }

    @Override
    public String toString()
    {
	return "Car [vin=" + vin + ", year=" + year + ", make=" + make + ", model=" + model + ", nickName=" + nickName
		+ ", username=" + username + "]";
    }

}
