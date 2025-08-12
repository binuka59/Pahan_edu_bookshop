package com.pahana.Service;

import com.pahana.Dao.ForgetDao;
import com.pahana.Model.Forget;

public class ForgetService {
	private static  ForgetService  instance;
    private ForgetDao forgetDao;

    private  ForgetService()
    {
        this.forgetDao = new ForgetDao();
    }

    public static ForgetService getInstance() {
        if (instance == null)
        {
            synchronized (LoginService.class) {
                if (instance == null) {
                    instance = new ForgetService();
                }
            }
        }
        return instance;
    }
    public void addcode(Forget forget) {
        ForgetDao.addData(forget);
    }

}
