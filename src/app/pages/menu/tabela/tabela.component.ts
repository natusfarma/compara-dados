import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Item } from 'src/app/smp/modals/item';
import { Resposta } from 'src/app/smp/modals/resposta';
import { AjustePipe } from 'src/app/smp/pipes/ajuste.pipe';
import { FiltrarColunaPipe } from 'src/app/smp/pipes/filtrar-coluna.pipe';

import { OrdenarTabelaPipe } from 'src/app/smp/pipes/ordenar-tabela.pipe';

@Component({
  selector: 'app-tabela',
  templateUrl: './tabela.component.html',
  styleUrls: ['./tabela.component.css']
})
export class TabelaComponent implements OnChanges, OnInit, OnDestroy {

  @Input() listas!: Resposta;
  tituloTabela: string = "Tabela"

  itensPorPagina: number = 1000;
  paginaAtual: number = 1;

  formulario!: FormGroup;
  valoresFiltrados: Array<any> = [];
  colunaFiltrada: string = '';


  tipo: string = '';
  colunas1: Array<string> = [];
  colunas2: Array<string> = [];
  colunasRemovidas: Array<string> = [];
  colunasFiltradas1: Array<string> = [];
  colunasFiltradas2: Array<string> = [];

  counter: number = 0
  atributoOrdenado: string = '';
  asc!: boolean;
  itec: Array<Item> = [];
  totvs: Array<Item> = [];
  iguais: Array<Item> = [];

  itecQtd: number = 0;
  totvsQtd: number = 0;
  iguaisQtd: number = 0;

  iguaisSubs: string = 'itec/totvs';

  tabela: Array<any> = [];
  tabelaFiltrada: Array<any> = [];


  private subscriptions: Subscription[] = []

  constructor(private ajuste: AjustePipe, private fb: FormBuilder,
    private activatedRoute: ActivatedRoute, private filtrarColunaPipe: FiltrarColunaPipe) {

  }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
  ngOnInit(): void {
    const routeSubscription = this.activatedRoute.params.subscribe(() => this.resetar())
    this.subscriptions.push(routeSubscription)
    this.formulario = this.fb.group({
      filtro: [''],
      coluna: ['']
    })
  }
  resetar() {
    this.iguais = []
    this.itec = []
    this.totvs = []
    this.atualizarLista([], '', 'Tabela')
    this.colunasRemovidas = []
  }
  ngOnChanges(changes: SimpleChanges) {

    if (changes['listas']) {

      if (this.listas) {


        this.iguais = this.listas.listaIguais
        this.itec = this.listas.listaNaoEncontrada;
        this.totvs = this.listas.listaSecundariaNaoEncontrada;
        this.colunaFiltrada = ''
        this.valoresFiltrados = []
        this.formulario.get('filtro')?.setValue('');
        this.formulario.get('coluna')?.setValue('');
        if (this.itec.length > 0) {
          this.atualizarLista(this.itec, 'itec', 'Itec')
        } else {
          this.atualizarLista([], '', 'Tabela')
        }
      }
    }

  }

  atualizarQtd() {

    this.iguaisQtd = this.filtrarColunaPipe.transform(this.iguais, this.colunaFiltrada, this.valoresFiltrados).length
    this.itecQtd = this.filtrarColunaPipe.transform(this.itec, this.colunaFiltrada, this.valoresFiltrados).length
    this.totvsQtd = this.filtrarColunaPipe.transform(this.totvs, this.colunaFiltrada, this.valoresFiltrados).length

  }

  atualizarLista(tabela: Item[], tipo: string, titulo: string) {
    this.tituloTabela = titulo;
    this.tipo = tipo
    this.mudarTabela(tabela, tipo)

  }

  mudarTabela(tabela: Item[], tipo: string) {

    this.iguaisSubs = tipo;

    this.colunas1 = []
    this.colunas2 = []

    if (tipo.includes('itec')) {
      this.colunas1 = Object.getOwnPropertyNames(tabela[0].obj1)
    }
    if (tipo.includes('totvs')) {
      this.colunas2 = Object.getOwnPropertyNames(tabela[0].obj2)
    }
    this.atualizarColunas()
    this.tabela = tabela;
    this.tabelaFiltrada = this.filtrarColunaPipe.transform(this.tabela, this.colunaFiltrada, this.valoresFiltrados);
    this.atualizarQtd()
  }

  atualizarColunas() {
    this.colunasFiltradas1 = this.colunas1.filter(col => !this.colunasRemovidas.includes(col))
    this.colunasFiltradas2 = this.colunas2.filter(col => !this.colunasRemovidas.includes(col))
  }

  ordenarLista(atributo: string, tipo: string) {
    if (this.atributoOrdenado != atributo || (this.atributoOrdenado === atributo && this.tipo != tipo)) {
      this.asc = false;
    }
    this.atributoOrdenado = atributo
    this.asc = !this.asc;
    this.tipo = tipo;
    this.tabelaFiltrada = new OrdenarTabelaPipe().transform(this.tabelaFiltrada, tipo, this.asc, atributo)


  }

  filtrar() {

    let valores = this.formulario.get('filtro')?.value;
    this.valoresFiltrados = valores ? valores.split(',') : [];
    this.tabelaFiltrada = this.filtrarColunaPipe.transform(this.tabela, this.colunaFiltrada, this.valoresFiltrados);
    this.atualizarQtd();
  }

  alterarFiltro() {
    this.colunaFiltrada = this.formulario.get('coluna')?.value;
  }


  removerColuna(coluna: string) {
    if (this.colunasRemovidas.includes(coluna)) {
      const index = this.colunasRemovidas.indexOf(coluna)
      this.colunasRemovidas.splice(index, 1)
    } else {
      this.colunasRemovidas.push(coluna)
      if (this.atributoOrdenado === coluna) {
        this.atributoOrdenado = '';
      }
      if (this.formulario.get('coluna')?.value === coluna) {
        this.formulario.get('coluna')?.setValue('');
        this.colunaFiltrada = '';

      }
    }
    this.atualizarColunas()
  }

  baixarTabela() {
    if (this.tituloTabela != 'Tabela') {
      let conteudo = this.obterDados();
      const fileName = this.tituloTabela + '.csv';
      const blob = new Blob([conteudo], { type: 'text/csv' });
      const link = document.createElement('a');
      link.href = URL.createObjectURL(blob);
      link.download = fileName;
      link.click();
    }
  }

  obterDados(): string {
    let tabela = this.tabelaFiltrada;
    let headers = this.colunasFiltradas1.map(col => this.ajuste.transform(col)).join(';')
    if (headers != '') {
      headers += ';'
    }
    headers += this.colunasFiltradas2.map(col => this.ajuste.transform(col)).join(';') + '\n';

    let resultado = tabela.map(item => {

      let valoresObj1 = this.colunasFiltradas1
        .map(col => {
          return item.obj1[col];
        }).join(';');

      let valoresObj2 = this.colunasFiltradas2
        .map(col => { return item.obj2[col] }
        ).join(';')

      if (valoresObj1 === '') {
        return valoresObj2;
      }
      return valoresObj1 + ';' + valoresObj2;
      
    }).join('\n');

    return headers + resultado;
  }



}
