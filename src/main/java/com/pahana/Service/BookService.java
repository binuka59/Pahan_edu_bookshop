package com.pahana.Service;

import com.pahana.Dao.BookDao;
import com.pahana.Dao.PaymentDao;
import com.pahana.Model.Book;
import com.pahana.Model.Payment;

import java.util.List;

public class BookService {
    private static  BookService  instance;
    private BookDao bookDao;

    private  BookService()
    {
        this.bookDao = new BookDao();
    }
	
    public static BookService getInstance() {
        if (instance == null)
        {
            synchronized (BookService.class) {
                if (instance == null) {
                    instance = new BookService();
                }
            }
        }
        return instance;
    }

	public static List<Book> getBook(String viewid) {
		return BookDao.getBook(viewid);
	}

    public static List<Book> getaddBook(String Bookid) {
        return BookDao.getaddBook(Bookid);
    }

    public static List<Payment> getaddbill(Integer mobile) {
        return PaymentDao.getaddbill(mobile);
    }
}
