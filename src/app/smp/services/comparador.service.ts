import { Injectable } from '@angular/core';
import { Item } from '../modals/item';
import { HttpClient} from '@angular/common/http'
import { Resposta } from '../modals/resposta';
import { Filial } from '../modals/filial';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class ComparadorService {

  private readonly API = environment.apiUrl;
  
  constructor(private http:HttpClient) { }


  processar(url:string){
    return this.http.get<Resposta>(this.API+url)
  }

  consultar(url:string){
    return this.http.get<Item[]>(this.API+url)
  }

  obterFiliais(url:string){
    return this.http.get<Filial[]>(this.API+url)
  }

}
