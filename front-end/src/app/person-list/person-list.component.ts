import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { Person } from '../create-person/person.model';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  persons:Array<Person>;

  constructor(public http:Http) { }

  ngOnInit() {
    this.getPersons();
  }

  getPersons(){
    this.http.get("http://localhost:8080/v1/person")
    .map(response=>response.json())
    .subscribe(res => this.persons = res);
  }

}
