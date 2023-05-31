import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tabela } from '../modals/tabela';
import { environment } from 'src/environments/environment';
import { map, Observable, of, tap } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class ToolbarService {


  private readonly API = environment.apiUrl;
  tabelas!:Array<Tabela>;
  grupos!:Array<string>;
  tabela = new EventEmitter<Tabela>();
  

  constructor(private router:Router,private http:HttpClient) {
   }
  novaAba(tabela: Tabela) {
    this.tabela.emit(tabela);
  }

  verificarTabela(url: string): Observable<Tabela | undefined> {
    if (!this.tabelas) {
      return this.http.get<Tabela[]>(this.API + '/menus').pipe(
        tap(tabelas => {
          this.tabelas = tabelas;
        }),
        map(() => this.tabelas.find(tabela => this.removerAcentos(tabela.nome).includes(url)))
      );
    }
  
    return of(this.tabelas.find(tabela => this.removerAcentos(tabela.nome).includes(url)));
  }

  obterTabela(url:string){
    let tabela =this.tabelas.find(tabela => this.removerAcentos(tabela.nome).includes(url))
    if(tabela){
      return tabela;
    }else{
      this.router.navigate(['tabelas'])
    }
    return
  }

  removerAcentos(str:string) {
    let s = str.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
    .replace(/\s/g, "")
    .toLocaleLowerCase();
    return s
  }

  obterGrupos(): Observable<string[]> {
    if (this.grupos) {
      return of(this.grupos);
    } else {
      return this.http.get<string[]>(this.API+'/menus/grupos').pipe(
        tap(grupos => {
          this.grupos = grupos; 
        })
      );
    }
  }

  obterTabelas(): Observable<Tabela[]> {
    if (this.tabelas) {
      return of(this.tabelas);
    } else {
      return this.http.get<Tabela[]>(this.API+'/menus').pipe(
        tap(tabelas => {
          this.tabelas = tabelas; 
        })
      );
    }
  }
}
