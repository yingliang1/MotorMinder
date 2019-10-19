package models;

public class Service
{
    private String type, shop, cost, service_date, insured, vin, username, serviceID;
    private int active;

    public Service(String type, String shop, String cost, String service_date, String insured, String vin,
	    String username, String serviceID, int active)
    {
	super();
	this.type = type;
	this.shop = shop;
	this.cost = cost;
	this.service_date = service_date;
	this.insured = insured;
	this.vin = vin;
	this.username = username;
	this.serviceID = serviceID;
	this.active = active;
    }

    public int getActive()
    {
	return active;
    }

    public void setActive(int active)
    {
	this.active = active;
    }

    public String getType()
    {
	return type;
    }

    public void setType(String type)
    {
	this.type = type;
    }

    public String getShop()
    {
	return shop;
    }

    public void setShop(String shop)
    {
	this.shop = shop;
    }

    public String getCost()
    {
	return cost;
    }

    public void setCost(String cost)
    {
	this.cost = cost;
    }

    public String getService_date()
    {
	return service_date;
    }

    public void setService_date(String service_date)
    {
	this.service_date = service_date;
    }

    public String getInsured()
    {
	return insured;
    }

    public void setInsured(String insured)
    {
	this.insured = insured;
    }

    public String getVin()
    {
	return vin;
    }

    public void setVin(String vin)
    {
	this.vin = vin;
    }

    public String getUsername()
    {
	return username;
    }

    public void setUsername(String username)
    {
	this.username = username;
    }

    public String getServiceID()
    {
	return serviceID;
    }

    public void setServiceID(String serviceID)
    {
	this.serviceID = serviceID;
    }
}