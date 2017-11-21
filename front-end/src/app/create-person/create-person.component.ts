import { Component, OnInit } from '@angular/core';
import { Http,HttpModule } from '@angular/http';
import { Person } from '../create-person/person.model';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-create-person',
  templateUrl: './create-person.component.html',
  styleUrls: ['./create-person.component.css']
})
export class CreatePersonComponent implements OnInit {

  person:Person;
  enableSubmit:boolean;

  constructor(public http:Http) { 
  }

  ngOnInit() {
  }

  formSubmit(){
    console.log("Hi There ",this.person);
    //this.http.post("http://localhost:4200/v1/person")
    //.map(response=>response.json()).subscribe(res=>console.log(res));
  }

  enableSubmitBtn(event:any){
    this.enableSubmit=!this.enableSubmit;
    console.log(event.target);
  }
  
}