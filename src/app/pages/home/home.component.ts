import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { fromEvent , debounceTime, Subscription } from "rxjs";
import { Tabela } from 'src/app/smp/modals/tabela';
import { ToolbarService } from 'src/app/smp/services/toolbar.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewInit,OnInit,OnDestroy {

  tabelas!:Array<Tabela>;
  grupos!:Array<string>;

  filtro = "";
  filtroGrupos = "";

  subscriptions:Subscription[] = [];

  @ViewChild('campoBusca') campoBusca!: ElementRef<HTMLInputElement>;

  constructor(private toolbarService:ToolbarService,private router:Router) {

  }
  ngOnDestroy(): void {
    this.subscriptions.forEach(element => {
      element.unsubscribe();
      
    })
  }
  ngOnInit(): void {

    let sub1 =  this.toolbarService.obterGrupos().subscribe((grupos) => this.grupos = grupos )
    let sub2 = this.toolbarService.obterTabelas().subscribe((tabelas) => this.tabelas = tabelas )
    this.subscriptions.push(sub1,sub2)
    
  }
  
  ngAfterViewInit() {
    fromEvent(this.campoBusca.nativeElement, 'keyup')
    .pipe(debounceTime(300) )
    .subscribe((e: Event) => {
        const target = e.target as HTMLInputElement;
        this.filtro = target.value;
      });
  }

  carregarTabela(tabela:Tabela){
    let url = this.removerAcentos(tabela.nome)
    this.router.navigate(['tabela',url])
  }
  
  filtrarGrupos(filtro:string){
    this.filtroGrupos = filtro;
  }

  removerAcentos(str:string) {
    let s = str.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
    .replace(/\s/g, "")
    .toLocaleLowerCase();
   
   
    return s
  }

}



