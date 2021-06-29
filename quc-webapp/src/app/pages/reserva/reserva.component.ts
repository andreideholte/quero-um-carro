import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { ApiService, Veiculos } from 'src/app/shared/api.service';

@Component({
  selector: 'app-reserva',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})
export class ReservaComponent implements OnInit {

  usuariologado: string = '';
  veiculosCollection!: Veiculos;

  constructor(
    private router: Router,
    private apiService: ApiService
  ) { }

  ngOnInit(): void {
    this.usuariologado = JSON.parse((localStorage.getItem('usuario') as string)).nome;

    this.apiService.listarVeiculos().subscribe((veiculos: Veiculos) => {
      this.veiculosCollection = veiculos;
    });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(["../../login"]);
  }
}
