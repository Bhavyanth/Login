package com.login.DAOImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.login.DAO.AdminDAO;
import com.login.entity.AdminDetail;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public int saveAdminDetail(AdminDetail detail){
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();	
			  int id = (Integer) session.save(detail);  
	            return id;  
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
		finally {
			session.flush();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int adminLogin(String emailId, String password) {  
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from AdminDetail where emailId=:emailId and password=:password");
			query.setParameter("emailId", emailId);
			query.setParameter("password", password);
			List<AdminDetail> list = query.list();
			int size = list.size();  
            if(size == 1)  
            {  
                return list.get(0).getAdminId();  
            }  
            else  
            {  
                return -1;  
            }  
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}finally {
			session.flush();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AdminDetail> getAdminData() {  
        Session session = null;  
        try  
        {  
            session = sessionFactory.getCurrentSession();  
              
            Query<AdminDetail> query = session.createQuery("from AdminDetail");  
            List<AdminDetail> list = query.list();  
              
            if(list.size() > 0)  
            {  
                return list;  
            }  
            else  
            {  
                return null;  
            }  
              
        }  
        catch(Exception exception)  
        {  
            System.out.println("Excecption while saving admin Details : " + exception.getMessage());  
            return null;  
        }  
        finally  
        {  
            session.flush();  
        }  
          
	}
	
}
