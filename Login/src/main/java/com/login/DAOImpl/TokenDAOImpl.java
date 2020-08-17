package com.login.DAOImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.login.DAO.TokenDAO;
import com.login.entity.Token;

@Repository("tokenDAO")
public class TokenDAOImpl implements TokenDAO{
	
	      
	    @Autowired  
	    SessionFactory sessionFactory;  
	  
	    public void saveUserEmail(String email, int adminId) {  
	        Session session = null;   
	        try  
	        {  
	            session = sessionFactory.getCurrentSession();  
	            Token t = new Token();  
	            t.setUserId(adminId);  
	            t.setEmailId(email);  
	            session.save(t);   
	        }  
	        catch(Exception exception)  
	        {  
	            System.out.println("Exception in saving UserEmail In Token Table :: " + exception.getMessage());  
	        }  
	        finally  
	        {  
	            session.flush();  
	        }  
	          
	    }  
	  
	    @SuppressWarnings("rawtypes")
		public boolean updateToken(String email, String authenticationToken, String secretKey) {  
	        Session session = null;  
	        try   
	        {  
	            session = sessionFactory.getCurrentSession();  
	            Query theQuery = null;        
	              
	            theQuery = session.createQuery("Update Token set authenticationToken = :authenticationToken , secretKey = :secretKey where emailId =:userEmail ");  
	                  
	            theQuery.setParameter("authenticationToken", authenticationToken);  
	            theQuery.setParameter("userEmail", email);  
	            theQuery.setParameter("secretKey", secretKey);  
	  
	            int result = theQuery.executeUpdate();  
	                      
	            if(result == 1)  
	            {  
	                return true;  
	            }  
	            else  
	            {  
	                return false;  
	            }  
	        }  
	        catch(Exception exception)  
	        {  
	            System.out.println(exception.getMessage());  
	            return false;  
	        }  
	        finally  
	        {  
	            session.flush();  
	        }             
	    }  
	  
	    @SuppressWarnings("unchecked")
		public int getTokenDetail(String email) {  
	        Session session = null;  
	        try  
	        {  
	            session = sessionFactory.getCurrentSession();  
	            Query<Token> query = session.createQuery("from Token where emailId =:userEmail");  
	            query.setParameter("userEmail", email);  
	              
	            List<Token> tokenDetails = query.list();  
	              
	            if(tokenDetails.size() > 0)  
	            {  
	                return tokenDetails.get(0).getTokenId();  
	            }  
	            else  
	            {  
	                return 0;  
	            }  
	              
	        }  
	        catch(Exception exception)  
	        {  
	            System.out.println(exception.getMessage());          
	        }  
	        finally  
	        {  
	            session.flush();  
	        }  
	          
	        return 0;  
	    }

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public int tokenAuth(String token, int emailId) {
			Session session = null;  
	          
	        try   
	        {  
	            session = sessionFactory.getCurrentSession();  
	              
	            Query query = session.createQuery("from Token where userID =:userID and authenticationToken = :token");  
	            query.setParameter("userID", emailId);  
	            query.setParameter("token", token);  
	            List<Token> tokenDetails = query.list();  
	              
	            if(tokenDetails.size() > 0)  
	            {  
	                return tokenDetails.get(0).getTokenId();  
	            }  
	            else  
	            {  
	                return 0;  
	            }  
	  
	        }  
	        catch(Exception exception)  
	        {  
	            return 0;  
	        }  
	        finally  
	        {  
	            session.flush();  
	        }  
	          
	          
	    }  
}
