import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService, Veiculos } from 'src/app/shared/api.service';

@Component({
  selector: 'app-veiculo',
  templateUrl: './veiculo.component.html',
  styleUrls: ['./veiculo.component.css']
})
export class VeiculoComponent implements OnInit {

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
