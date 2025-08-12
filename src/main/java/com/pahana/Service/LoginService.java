package com.pahana.Service;

import com.pahana.Dao.CustomerDao;
import com.pahana.Dao.ItemDao;
import com.pahana.Dao.LoginDao;
import com.pahana.Dao.PaymentDao;
import com.pahana.Model.*;

import java.sql.SQLException;
import java.util.List;

public class LoginService {
    private static  LoginService  instance;
    private LoginDao loginDao;
    private static ItemDao itemDao;
    private static CustomerDao customerDao;

    private  LoginService()
    {

        this.loginDao = new LoginDao();
        this.customerDao = new CustomerDao();
        this.itemDao = new ItemDao();
    }

    public static LoginService getInstance() {
        if (instance == null)
        {
            synchronized (LoginService.class) {
                if (instance == null) {
                    instance = new LoginService();
                }
            }
        }
        return instance;
    }


    public static List<Customer> getAllCustomer() {
        return customerDao.getAllCustomer();
    }

    public static List<Item> getAllItems()
    {
        return  itemDao.getAllItems();
    }

    public static List<Item> getAllCategory()
    {
        return  itemDao.getAllCategory();
    }

    public static List<Payment> getDaily()
    {
        return PaymentDao.getDaily();
    }

    public static List<Payment> getMonthly()
    {
        return PaymentDao.getMonthly();
    }


    public List<Login> getAllLogin() throws SQLException {
        return loginDao.getAllLogin();
    }

	public static List<Book> getAllBook() {
		return  LoginDao.getAllBook();
		
	}


}





