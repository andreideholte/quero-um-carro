import { Component, Directive, Inject, Injectable, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { DateRange, MatDateRangeSelectionStrategy, MAT_DATE_RANGE_SELECTION_STRATEGY } from '@angular/material/datepicker';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';
import { concatMap } from "rxjs/operators";
import { ApiService, Reserva, Usuario, Veiculo } from 'src/app/shared/api.service';
import { HeaderCard } from 'src/app/shared/components/card/card.component';
import { MessageService } from 'src/app/shared/message.service';

@Injectable()
export class MaxDayRangeSelectionStrategy<D> implements MatDateRangeSelectionStrategy<D> {

  public delta: number;
  constructor(private _dateAdapter: DateAdapter<D>) {}

  selectionFinished(date: D, currentRange: DateRange<D>) {
    let { start, end } = currentRange;
    if (start == null || (start && end)) {
      start = date;
      end = null;
    } else if (end == null) {
      const maxDate = this._dateAdapter.addCalendarDays(start, this.delta);
      end = date ? (date > maxDate ? maxDate : date) : null;
    }

    return new DateRange<D>(start, end);
  }

  createPreview(
    activeDate: D | null,
    currentRange: DateRange<D>
  ): DateRange<D> {
    if (currentRange.start && !currentRange.end) {
      const maxDate = this._dateAdapter.addCalendarDays(
        currentRange.start,
        this.delta
      );
      const rangeEnd = activeDate
        ? activeDate > maxDate
          ? maxDate
          : activeDate
        : null;

      return new DateRange(currentRange.start, rangeEnd);
    }

    return new DateRange<D>(null, null);
  }
}

@Directive({
  selector: "[maxRange]",
  providers: [
    {
      provide: MAT_DATE_RANGE_SELECTION_STRATEGY,
      useClass: MaxDayRangeSelectionStrategy
    }
  ]
})
export class MaxRangeDirective {
  constructor(
    @Inject(MAT_DATE_RANGE_SELECTION_STRATEGY)
    private maxRangeStrategy: MaxDayRangeSelectionStrategy<any>
  ) {}
  @Input() set maxRange(value: number) {
    this.maxRangeStrategy.delta = +value || 7;
  }
}

@Component({
  selector: 'app-reserva',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css'],
})
export class ReservaComponent implements OnInit {

  usuariologado!: Usuario;
  veiculoEscolhido!: Veiculo;
  genericDataFormGroup!: FormGroup;

  headerMetadados: HeaderCard = {icon: 'assignment', titleBold: 'Dados', titleRegular: ' da sua Reserva'};
  headerGaleria: HeaderCard = {icon: 'photo_library', titleBold: 'Imagem', titleRegular: ' do Veículo'};

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private apiService: ApiService,
    private messageService: MessageService
  ) {
    this.createForm();
  }

  createForm() {
		this.genericDataFormGroup = this.fb.group({
      dataInicio: [moment(new Date()), Validators.required],
      dataFim: [moment(new Date()).add(30, 'days'), Validators.required]
		});
  }

  ngOnInit(): void {
    this.usuariologado = JSON.parse((localStorage.getItem('usuario') as string));

    this.route.params.pipe(
      concatMap((params: any) => this.apiService.buscarVeiculo(params['idVeiculo']))

      ).subscribe((veiculo: Veiculo) => {
        this.veiculoEscolhido = veiculo;
    });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(["../../login"]);
  }

  criarReserva() {
    if (this.genericDataFormGroup.valid) {
      if (moment(this.genericDataFormGroup.value.dataInicio).isBefore(moment(new Date()))) {
        this.messageService.enviarMensagem("Data de inicio não pode ser menor ou igual a hoje!");
        return;
      }

      const reserva : Reserva = {
        dataInicio: this.genericDataFormGroup.value.dataInicio,
        dataFim: this.genericDataFormGroup.value.dataFim,
        veiculo: this.veiculoEscolhido,
        usuario: this.usuariologado
      }

      this.apiService.criarReserva(reserva)
          .subscribe(() => {
            this.messageService.enviarMensagem("Reserva efetuada com sucesso!");
          }, (exception) => {
            this.messageService.enviarMensagem(exception.error);
          })
    }
  }
}
