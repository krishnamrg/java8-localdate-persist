import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CreatePersonComponent} from './create-person/create-person.component';
import {PersonListComponent} from './person-list/person-list.component'

const routes: Routes = [
  {
    path:'',
    component:PersonListComponent
  },{
    path:'create',
    component:CreatePersonComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
