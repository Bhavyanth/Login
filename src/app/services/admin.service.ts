import { Injectable } from '@angular/core';
import { Http, RequestOptions , Headers } from '@angular/http';  
import { Observable } from 'rxjs';  
import { AdminDetail } from '../classes/admin-detail';  
import { Router } from '@angular/router';  
  
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private  baseUrl = "http://localhost:8080/LoginPage/api/";  
  constructor(private http: Http, private router : Router) { }

  saveAdminDetails(adminDetail : AdminDetail) : Observable<any>  
  {  
      let url = this.baseUrl + "saveAdmin";  
      return this.http.post(url,adminDetail);  
  }  
  
  login(adminDetail : AdminDetail) : Observable<any>  
  {  
      let url = this.baseUrl + "login";  
      return this.http.post(url, adminDetail);  
  }  
  
  logout()   
  {   
    localStorage.removeItem('token');  
    this.router.navigate(['']);  
  
  }  
  
  isLoggedIn() {   
   
    let jwtHelper = new JwtHelperService();    
    let token = localStorage.getItem('token');    
    if(!token)  
    {  
      return false;  
    }  
  
    if(token)  
    {  
      let expirationDate = jwtHelper.getTokenExpirationDate(token);  
  
      let isExpired = jwtHelper.isTokenExpired(token);  
  
      return !isExpired;      
    }     
  }  
    
    
  getAdminDetail(adminId) : Observable<any>  
  {  
      let url = this.baseUrl + "getAdminData/" + adminId;  
  
       // create an instance of Header object.  
      let headers = new Headers();  
  
      // get token from localStorage.  
      let token = localStorage.getItem('token');  
  
      // Append Authorization header.  
      headers.append('Authorization' , 'Bearer ' + token);  
  
      // create object of RequestOptions and include that in it.  
      let options = new RequestOptions( { headers : headers } );  
  
      return this.http.get(url , options);  
  }  

}
