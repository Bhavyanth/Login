import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';  
import { ActivatedRoute, Router } from '@angular/router'; 

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
   adminId;
   haveData= 0;
   data= [];
   dataRequest = false;

  constructor(public adminService : AdminService, public route: ActivatedRoute, public router: Router) { }

  ngOnInit() {
    if((this.adminService.isLoggedIn()) )  
    {  
      this.route.paramMap.subscribe(params => {  
        this.adminId =+ params.get('adminId');  
      });  
    }  
    else  
    {  
        this.router.navigate(['/login']);  
    }  
  }

  getAdminData()  
  {  
      this.haveData = 0;  
  
      this.dataRequest = true;  
  
      this.adminService.getAdminDetail(this.adminId).subscribe(  
          response => {  
  
              let result = response.json();  
              this.data = result;  
  
              if(result == " ")  
              {  
                  this.haveData = 0;  
              }  
              else  
              {  
                this.haveData = this.haveData + 1;  
              }  
          },  
          error => {  
              console.log("Error while getting Admin Data");  
          }  
      );  
  }  

}
