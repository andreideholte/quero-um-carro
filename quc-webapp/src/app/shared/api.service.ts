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

    saveMidia(midia: any) {
        console.log('midia: ', midia);
        let midias = JSON.parse((localStorage.getItem('midias') as string));
        console.log('midias: ', midias);

        if (!midias || midias?.length  < 1) {
            midias = [];
        }

        midias.push(midia);

        localStorage.removeItem('midias');
        localStorage.setItem('midias', JSON.stringify(midias));
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
  veiculos: [{
    id: string;
    marca: string;
    modelo: string;
    ano: string;
    cor: string;
    quilometragem: string;
    imagem: string;
  }]
}
