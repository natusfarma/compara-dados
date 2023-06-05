import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ToolbarComponent } from './componentes/toolbar/toolbar.component';
import { HomeComponent } from './pages/home/home.component';
import { FiltrarGruposPipe,} from './smp/pipes/filtrar-grupos.pipe';
import { AppRoutingModule } from './app-routing.module';
import { TabelaComponent } from './pages/menu/tabela/tabela.component';
import { AjustePipe } from './smp/pipes/ajuste.pipe';
import { MenuComponent } from './pages/menu/menu.component';
import { LeadingZeroPipe } from './smp/pipes/leading-zero.pipe';
import { OrdenarTabelaPipe } from './smp/pipes/ordenar-tabela.pipe';
import { FiltrarColunaPipe } from './smp/pipes/filtrar-coluna.pipe';
import { VerificarDataPipe } from './smp/pipes/verificar-data.pipe';
import { NgxPaginationModule } from 'ngx-pagination';




@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    HomeComponent,
    FiltrarGruposPipe,
    TabelaComponent,
    AjustePipe,
    MenuComponent,
    LeadingZeroPipe,
    OrdenarTabelaPipe,
    FiltrarColunaPipe,
    VerificarDataPipe,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule,
    FormsModule
    
  ],
  providers: [AjustePipe,OrdenarTabelaPipe,FiltrarColunaPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
