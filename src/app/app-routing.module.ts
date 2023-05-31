import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { MenuComponent } from './pages/menu/menu.component';

const routes: Routes = [
  {
    path:"tabela",component:HomeComponent
  },
  {
    path:"tabela/:id",component:MenuComponent
  },
  {
    path: '',pathMatch: 'full',redirectTo: 'tabela' 
  }, 
  {
    path:"**",redirectTo:"tabela"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
