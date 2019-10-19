package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class DBhelper
{
    // selects

    public static boolean checkValidUser(String un) throws SQLException
    {
	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	psmt = connection.prepareStatement("select idUser from user where username=?;");
	try
	{
	    psmt.setString(1, un);
	    rs = psmt.executeQuery();

	} catch (SQLException e)
	{
	    System.out.print("Could not find user");
	}
	if (rs.next())
	{
	    psmt.close();
	    connection.close();
	    return true;
	} else
	{
	    psmt.close();
	    connection.close();
	    return false;
	}
    }
    
  public static String attemptToLoginUser(String username, String password) throws SQLException {
    Connection connection = MySQLDBHelper.getInstance().databaseconnect();
    String idUser = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    psmt = connection.prepareStatement("SELECT firstName FROM user WHERE username=? AND password=?;");
    try {
      psmt.setString(1, username);
      psmt.setString(2, password);
      rs = psmt.executeQuery();
    } catch (SQLException e) {
      System.out.print("Unable to execute loginUser query");
    }
    if (rs.next()) {
      idUser = rs.getString(1);
    }
    
    psmt.close();
    connection.close();
    return idUser;
  }

    public static ArrayList<Car> selectUserCars(String un) throws SQLException
    {
	ArrayList<Car> listOfCars = new ArrayList<Car>();
	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	psmt = connection.prepareStatement("select year, make, model, vin, nickName from car where username=?;");
	try
	{
	    psmt.setString(1, un);
	    rs = psmt.executeQuery();

	} catch (SQLException e)
	{
	    System.out.print("Could not find user");
	}

	while (rs.next())
	{
	    Car car = new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), un,
		    rs.getInt(7), 1);
	    listOfCars.add(car);
	}

	psmt.close();
	connection.close();

	return listOfCars;
    }

    public static String selectUserRealName(String un) throws SQLException
    {
	String firstName = null;
	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	psmt = connection.prepareStatement("select firstName from user where username=?;");
	try
	{
	    psmt.setString(1, un);
	    rs = psmt.executeQuery();

	} catch (SQLException e)
	{
	    System.out.print("Could not find user");
	}
	if (rs.next())
	{
	    firstName = rs.getString(1);
	    psmt.close();
	    connection.close();
	    return firstName;
	} else
	{
	    psmt.close();
	    connection.close();
	    return "MotorMinder User";
	}
    }

    public static void selectCarYear() throws Exception
    {

	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
	PreparedStatement psmt = null;
	psmt = connection.prepareStatement("select year from car where idCar=?;");

	try
	{
	    psmt.setInt(1, 1);
	    ResultSet rs = psmt.executeQuery();
	    while (rs.next())
	    {

		String year = rs.getString("year");
		System.out.print("year: " + year);
	    }
	} catch (SQLException e)
	{
	    System.out.print("error");
	}
    }

    // inserts

    public static void insertUser(String userName, String password, String firstName, String lastName, String email,
	    String phoneNumber) throws Exception
    {

	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
	PreparedStatement psmt = null;
	psmt = connection.prepareStatement("INSERT INTO user "
		+ "(username, password, firstName, lastName, email, phoneNum) " + "VALUES (?, ?, ?, ?, ?,?);");
	try
	{

	    psmt.setString(1, userName);
	    psmt.setString(2, password);
	    psmt.setString(3, firstName);
	    psmt.setString(4, lastName);
	    psmt.setString(5, email);
	    psmt.setString(6, phoneNumber);
	    int rs = psmt.executeUpdate();
	    if (rs >= 0)
	    {
		psmt.close();
		connection.close();
		System.out.println("insert success");
	    } else
	    {
		psmt.close();
		connection.close();
		System.out.println("insert failed");
	    }
	} catch (SQLException e)
	{
	    System.out.print("User Insert Failed");
	    e.printStackTrace();
	}
    }

    public static void insertCar(String year, String make, String model, String vin, String nickName, String username)
	    throws Exception
    {

	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
	PreparedStatement psmt = null;
	psmt = connection.prepareStatement(
		"INSERT INTO car " + "(year, make, model, vin, nickName, username) " + "VALUES (?, ?, ?, ?, ?,?);");
	try
	{

	    psmt.setString(1, year);
	    psmt.setString(2, make);
	    psmt.setString(3, model);
	    psmt.setString(4, vin);
	    psmt.setString(5, nickName);
	    psmt.setString(6, username);
	    int rs = psmt.executeUpdate();
	    if (rs >= 0)
	    {
		psmt.close();
		connection.close();
		System.out.println("insert success");
	    } else
	    {
		psmt.close();
		connection.close();
		System.out.println("insert failed");
	    }
	} catch (SQLException e)
	{
	    System.out.print("User Insert Failed");
	}
    }

    // deletes

    public static void dbDeleteCar(String vin) throws Exception
    {

	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
	PreparedStatement psmt = null;
	psmt = connection.prepareStatement("delete from car where vin=?;");
	psmt.setString(1, vin);
	int i = psmt.executeUpdate();
	if (i >= 0)
	{
	    psmt.close();
	    connection.close();
	    System.out.println("delete success");
	} else
	{
	    psmt.close();
	    connection.close();
	    System.out.println("delete failed");
	}

    }

    public static void dbUpdate() throws Exception
    {

	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
	PreparedStatement psmt = null;
	psmt = connection.prepareStatement("update car set year=? where username=?;");
	psmt.setString(1, "2018");
	psmt.setString(2, "ying");
	int i = psmt.executeUpdate();
	if (i >= 0)
	{
	    psmt.close();
	    connection.close();
	    System.out.println("update success");
	} else
	{
	    psmt.close();
	    connection.close();
	    System.out.println("update failed");
	}
    }

    public static String queryCar(String uname) throws ClassNotFoundException, SQLException {
    	Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		String results = "";
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Car WHERE username=?"); 
		
		ps.setString(1, uname); 
		ResultSet rs = ps.executeQuery();
		List<Car> cars = new ArrayList<>();
		while (rs.next()) {
			String year = rs.getString("year");
			String make = rs.getString("make");

			String vin = rs.getString("vin");
			String name = rs.getString("username"); 
			String model = rs.getString("model");
			String nname = rs.getString("nickname");
			int mileage = rs.getInt("mileage");
			
			Car tmp = new Car(year, make, model, vin, nname, name, mileage, 1);
			cars.add(tmp);
		}
		Car[] carArr = cars.toArray(new Car[cars.size()]);
		
		Gson gson = new Gson();
		results += gson.toJson(carArr, Car[].class);
		System.out.println(results);

		connection.close();
		return results;
	}
	
	public static String queryService(String vin) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		String results = "";
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Service WHERE vin=?"); 
		
		ps.setString(1, vin); 
		ResultSet rs = ps.executeQuery(); 
		List<Service> services = new ArrayList<>();
		while (rs.next()) {
			String type = rs.getString("type");
			String shop = rs.getString("shop");

			String cost = rs.getString("cost");
			String service_date = rs.getString("date"); 
			String insured = rs.getString("insured");
			String username = rs.getString("username");
			String serviceID = rs.getString("idservice");
			
			Service tmp = new Service(type, shop, cost, service_date, insured, vin, username, serviceID, 1);
			services.add(tmp);
		}
		Service[] serArr = services.toArray(new Service[services.size()]);
		
		Gson gson = new Gson();
		results += gson.toJson(serArr, Service[].class);
		System.out.println(results);

		connection.close();
		return results;
	}
	
	public static String queryServiceByUsername(String username) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		String results = "";
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Service WHERE username=?"); 
		
		ps.setString(1, username); 
		ResultSet rs = ps.executeQuery(); 
		List<Service> services = new ArrayList<>();
		while (rs.next()) {
			String type = rs.getString("type");
			String shop = rs.getString("shop");

			String cost = rs.getString("cost");
			String service_date = "";
			try {
				service_date = rs.getString("date"); 
			} catch (Exception e) {
			}
			String insured = rs.getString("insured");
			String vin = rs.getString("vin");
			String serviceID = rs.getString("idservice");
			
			Service tmp = new Service(type, shop, cost, service_date, insured, vin, username, serviceID, 1);
			services.add(tmp);
		}
		Service[] serArr = services.toArray(new Service[services.size()]);
		
		Gson gson = new Gson();
		results += gson.toJson(serArr, Service[].class);
		System.out.println(results);

		connection.close();
		return results;
	}
	
	public static void updateCar(String message) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		PreparedStatement ps = connection.prepareStatement("update car set year=?, make=?, model=?,"
				+ "nickName=?,mileage=? where vin=?;"); 
		
		Gson gson = new Gson();
		
		try {
			Car tmp = gson.fromJson(message, Car.class);
			System.out.println(tmp.toString());
			ps.setString(1, tmp.getYear());
			ps.setString(2, tmp.getMake());
			ps.setString(3, tmp.getModel());
			ps.setString(4, tmp.getNickName());
			ps.setInt(5, tmp.getMileage());
			ps.setString(6, tmp.getVin());
			System.out.println(ps.toString());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		ps.close();
		connection.close();
	}
	
	public static void updateService(String message) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		PreparedStatement ps = connection.prepareStatement("update service set type=?, shop=?, cost=?,"
				+ "date=?, vin=? where idService=?;"); 
		
		Gson gson = new Gson();
		
		try {
			Service tmp = gson.fromJson(message, Service.class);
			System.out.println(tmp.toString());
			ps.setString(1, tmp.getType());
			ps.setString(2, tmp.getShop());
			ps.setString(3, tmp.getCost());
			ps.setString(4, tmp.getService_date());
			ps.setString(5, tmp.getVin());
			ps.setString(6, tmp.getServiceID());
			System.out.println(ps.toString());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		ps.close();
		connection.close();
	}

	public static void insertCar(String message) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO car (year, make, model, vin, nickName, username, mileage)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?);"); 
		
		Gson gson = new Gson();
		
		try {
			Car tmp = gson.fromJson(message, Car.class);
			System.out.println(tmp.toString());
			ps.setString(1, tmp.getYear());
			ps.setString(2, tmp.getMake());
			ps.setString(3, tmp.getModel());
			ps.setString(5, tmp.getNickName());
			ps.setInt(7, tmp.getMileage());
			ps.setString(4, tmp.getVin());
			ps.setString(6, tmp.getUsername());
			System.out.println(ps.toString());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		ps.close();
		connection.close();
	}

	public static void insertService(String message) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO service (type, shop, cost, date, vin, username)"
				+ " VALUES (?, ?, ?, ?, ?, ?);"); 
		System.out.println("insertService: " + message);
		Gson gson = new Gson();
		
		try {
			Service tmp = gson.fromJson(message, Service.class);
			System.out.println(tmp.toString());
			ps.setString(1, tmp.getType());
			ps.setString(2, tmp.getShop());
			ps.setString(3, tmp.getCost());
			ps.setString(4, tmp.getService_date());
			ps.setString(5, tmp.getVin());
			ps.setString(6, tmp.getUsername());
			System.out.println(ps.toString());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		ps.close();
		connection.close();
	}
	
  public static List<Service> getServicesDueNextWeek() {
    Connection connection = MySQLDBHelper.getInstance().databaseconnect();
    List<Service> services = new ArrayList<>();
    
    PreparedStatement ps;
    try {
      ps = connection.prepareStatement("SELECT * FROM Service WHERE date BETWEEN NOW() AND (NOW() + INTERVAL 1 WEEK);");
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        String vin = rs.getString("vin");
        String username = rs.getString("username");
        String type = rs.getString("type");
        String shop = rs.getString("shop");
        String insured = rs.getString("insured");
        String serviceId = rs.getString("idService");
        String serviceDate = rs.getString("date");
        String cost = rs.getString("cost");
        int active = rs.getInt("active");
        
        Service service = new Service(type, shop, cost, serviceDate, insured, vin, username, serviceId, active);
        
        services.add(service);
      }
      
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return services;
  }
  
  public static User getUserByUsername(String username) {
    Connection connection = MySQLDBHelper.getInstance().databaseconnect();
    User user = null;
    
    PreparedStatement ps;
    try {
      ps = connection.prepareStatement("SELECT * FROM user WHERE username=?");
      ps.setString(1, username);
      
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        user = new User();
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setIdUser(rs.getInt("idUser"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNum(rs.getString("phoneNum"));
      }
      
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user;
  }
  
  public static Car getCarByVin(String vin) {
    Connection connection = MySQLDBHelper.getInstance().databaseconnect();
    Car car = null;
    
    PreparedStatement ps;
    try {
      ps = connection.prepareStatement("SELECT * FROM car WHERE vin=?");
      ps.setString(1, vin);
      
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        car = new Car();
        car.setMake(rs.getString("make"));
        car.setMileage(rs.getInt("mileage"));
        car.setModel(rs.getString("model"));
        car.setNickName(rs.getString("nickName"));
        car.setUsername(rs.getString("username"));
        car.setVin(vin);
        car.setYear(rs.getString("year"));
      }
      
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return car;
  }
}
