package com.login.DAO;

import java.util.List;

import com.login.entity.AdminDetail;

public interface AdminDAO {
	public int saveAdminDetail(AdminDetail adminDetail);  
    
    public int adminLogin(String emailId , String password);  
      
    public List<AdminDetail> getAdminData();  
}
