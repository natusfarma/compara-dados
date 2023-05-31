import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tabela } from 'src/app/smp/modals/tabela';
import { ToolbarService } from 'src/app/smp/services/toolbar.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  tabelas:Tabela[] = [];

  constructor(private route: Router,private toolbarService:ToolbarService) {

  }
  ngOnInit(): void {
  this.toolbarService.tabela.subscribe(tabela =>(this.verificarTabela(tabela)))
  }

  verificarTabela(tabela:Tabela){
    let existe = this.tabelas.some(item => item === tabela)
    if(!existe){
      this.tabelas.push(tabela)
    }
  }

  removerAcentos(str:string) {
    let s = str.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
    .replace(/\s/g, "")
    .toLocaleLowerCase();
   
   
    return s
  }

  fecharAba(index:number){
    let tabela = this.tabelas[index];
    
    this.tabelas.splice(index,1);
    if( this.route.url.includes(tabela.nome)){
      if(index > 0){
        this.route.navigate(['tabela',this.removerAcentos(this.tabelas[index-1].nome)])
      }else{
        this.route.navigate(['tabela'])
      }
    }
    
   
  }

  navegar(rota:string){
    let url = this.removerAcentos(rota)
    this.route.navigate(['tabela',url])
  }

}
