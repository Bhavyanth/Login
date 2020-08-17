package com.login.service.interfaces;

import java.util.List;

import com.login.entity.AdminDetail;

public interface AdminService {
	public int saveAdminDetail(AdminDetail adminDetail);  
    
    public int adminLogin(String emailId , String password);  
      
    public List<AdminDetail> getAdminData();
}
