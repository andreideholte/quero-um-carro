import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { Observable } from "rxjs";

const httpHeaders = new HttpHeaders({
  'Content-type': 'application/json'
});

const URL_API: string = environment.qucApiUrl;

@Injectable({
    providedIn: 'root',
})
export class ApiService {

  constructor(private http: HttpClient) {}

    login(email: string, nome: string) {
      let myParams = new HttpParams();
      myParams = myParams.set('email', email);
      myParams = myParams.set('nome', nome);

      let options = {
        headers: httpHeaders,
        params: myParams,
        observe: 'body' as 'body',
        responseType: 'json' as 'json'
      };

      return this.http.get<any>(`${URL_API}/auth/login`, options);
    }

    criarReserva(reserva: Reserva) {
      let options = {
        headers: httpHeaders,
        observe: 'body' as 'body',
        responseType: 'json' as 'json'
      };

      return this.http.post<Reserva>(`${URL_API}/reserva`, reserva, options);
    }

    buscarVeiculo(idVeiculo: string): Observable<Veiculo> {
      let options = {
        headers: httpHeaders,
        observe: 'body' as 'body',
        responseType: 'json' as 'json'
      };

      return this.http.get<Veiculo>(`${URL_API}/veiculo/${idVeiculo}`, options);
    }

    listarVeiculos(): Observable<Veiculos> {
      let options = {
        headers: httpHeaders,
        observe: 'body' as 'body',
        responseType: 'json' as 'json'
      };

      return this.http.get<Veiculos>(`${URL_API}/veiculo`, options);
    }
}

export interface Veiculos {
  veiculos: [Veiculo]
}

export interface Veiculo {
  id: string;
  marca: string;
  modelo: string;
  ano: string;
  cor: string;
  quilometragem: string;
  imagem: string;
}

export interface Reserva {
  dataInicio: string;
  dataFim: string;
  veiculo: Veiculo;
  usuario: Usuario;
}


export interface Usuario {
  id: string;
  email: string;
  nome: string;
}
