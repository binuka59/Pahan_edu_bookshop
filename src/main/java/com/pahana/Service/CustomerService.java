package com.pahana.Service;

import com.pahana.Dao.CustomerDao;

public class CustomerService {
    private static  CustomerService  instance;
    private CustomerDao customerDao;

    private  CustomerService()
    {
        this.customerDao = new CustomerDao();
    }

    public static CustomerService getInstance() {
        if (instance == null)
        {
            synchronized (CustomerService.class) {
                if (instance == null) {
                    instance = new CustomerService();
                }
            }
        }
        return instance;
    }


}
