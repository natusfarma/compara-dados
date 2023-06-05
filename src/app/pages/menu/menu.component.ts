import { Component, OnDestroy, OnInit, } from '@angular/core';
import { ActivatedRoute, Router, } from '@angular/router';
import { Tabela } from 'src/app/smp/modals/tabela';
import { ToolbarService } from 'src/app/smp/services/toolbar.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { modeloUrlMetodos } from 'src/app/smp/modals/modeloUrlMetodos';
import { Resposta } from 'src/app/smp/modals/resposta';
import { ComparadorService } from 'src/app/smp/services/comparador.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, OnDestroy {


  private subscriptions: Subscription[] = []
  tabela!: Tabela;
  listas!: Resposta;
  filtroAtivo!: modeloUrlMetodos;
  processado: boolean = false;
  miliNum: number = 0;
  segNum: number = 0;
  minNum: number = 0;

  dadosRecebidos: boolean = true;
  mensagem: string = '';
  tempoDecorrido!: string;
  timer: any;
  erro: boolean = false;
  formulario!: FormGroup;
  tituloTabela: string = "Tabela";
  reset: boolean = false;

  constructor(private comparadorService: ComparadorService, private fb: FormBuilder,
    private activatedRoute: ActivatedRoute, private router: Router, private toolbarService: ToolbarService) { }

  ngOnInit(): void {
    this.formulario = new FormGroup({})
    const routeSubscription = this.activatedRoute.params.subscribe(rota => (this.atualizarTabela(rota['id'])
    ))
    this.subscriptions.push(routeSubscription)
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  atualizarTabela(rota: string) {
    this.resetar()

    this.toolbarService.verificarTabela(rota).subscribe(res => {
      if (res === undefined) {
        this.router.navigate(['tabelas'])
      } else {

        this.tabela = res
        this.toolbarService.novaAba(res)
        this.ativarFiltro(this.tabela.modeloUrlMetodos[0])
        this.criarFormulario()

      }
    });

  }

  criarFormulario() {
    let tipo = this.filtroAtivo.tipos;
    this.formulario = this.fb.group({})
    for (let i = 0; i < tipo.length; i++) {
      const e = tipo[i] + '_' + i;
      this.formulario.addControl(e, this.fb.control('', Validators.required))
    }

  }

  ativarFiltro(filtro: modeloUrlMetodos) {
    for (let i = 0; i < this.tabela.modeloUrlMetodos.length; i++) {
      const f = this.tabela.modeloUrlMetodos[i];
      if (filtro === f) {
        f.ativo = true;
        this.filtroAtivo = f;
      } else {
        f.ativo = false;
      }
    }
    this.criarFormulario()
  }

  processar() {
    this.processado = true;
    this.dadosRecebidos = false;
    this.erro = false;
    this.mensagem = '';
    let url = this.tabela.urlClass + this.parametros();

    this.iniciar()

    this.listas = {
      listaIguais:[],
      listaNaoEncontrada:[],
      listaSecundariaNaoEncontrada:[]
    }

    this.comparadorService.processar(url)
      .subscribe((res) => {
        this.parar()
        this.dadosRecebidos = true;

        this.listas = res;
        if (this.listas.listaIguais.length === 0 &&
          this.listas.listaNaoEncontrada.length === 0 &&
          this.listas.listaSecundariaNaoEncontrada.length === 0) {
          this.mensagem = "Nenhum registro encontrado."
        }
      },
        (err) => {
          
          this.erro = true;
          this.dadosRecebidos = true;
          this.parar()
        }
      )


  }


  parametros(): string {
    let dados = '';
    if (this.filtroAtivo.tipos[0] === 'date') {


      let dataInicio: Date = this.formulario.get('date_0')?.value;
      let dataFim: Date = this.formulario.get('date_1')?.value;
      dados = `?ini=${dataInicio}&fim=${dataFim}`;
    } else {
      let ids = this.formulario.get('text_0')?.value;
      
      
      dados = '/?ids=' + ids;
    }

    return this.filtroAtivo.url + dados;
  }

  resetar() {
    this.parar()
    this.miliNum = 0;
    this.segNum = 0;
    this.minNum = 0;

  }

  iniciar() {
    this.resetar()
    this.timer = setInterval(() => {
      this.milissegundos()
    }, 10)
  }

  milissegundos() {
    this.miliNum++;

    if (this.miliNum == 99) {
      this.miliNum = 0
      this.segundos()
    }
  }

  segundos() {
    this.segNum++;
    if (this.segNum == 59) {
      this.segNum = 0;
      this.minutos()
    }
  }

  minutos() {
    this.minNum++;
  }
  parar() {
    clearInterval(this.timer)
  }
}
