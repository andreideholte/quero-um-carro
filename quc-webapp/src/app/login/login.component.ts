import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService, Usuario } from '../shared/api.service';

@Component({
  selector: 'app-login',
  template: `
    <div class="flex-container">
      <div class="flex-item">
        <div class="card login-page bg-white shadow rounded border-0">
          <div class="card-body">
            <span class="card-title text-center"><img src="assets/quc-logo.png"></span>
            <br />
            <form class="login-form" [formGroup]="loginForm" autocomplete="off">
              <mat-form-field class="full-width" appearance="outline">
                <mat-label>Email</mat-label>
                <mat-icon matPrefix>email</mat-icon>
                <input matInput placeholder="Email"
                        formControlName="email"
                        autocomplete="off"
                        required pattern="[a-zA-Z0-9.-_]{1,}@[a-zA-Z0-9.-]{1,}[.]{1}[a-zA-Z]{1,}">
              </mat-form-field>
              <div *ngIf="loginForm.get('email').invalid && loginForm.get('email').errors &&
                      (loginForm.get('email').dirty || loginForm.get('email').touched)">
                  <small class="text-danger"
                      *ngIf="loginForm.get('email').hasError('required')">
                      O campo é obrigatório.
                  </small>
                  <small class="text-danger"
                      *ngIf="loginForm.get('email').hasError('pattern')">
                      O campo precisa ter um formato de email válido.
                  </small>
              </div>
              <br />
              <mat-form-field class="full-width" appearance="outline">
                <mat-label>Nome</mat-label>
                <mat-icon matPrefix>person</mat-icon>
                <input matInput
                      autocomplete="off"
                      pattern=".{3,}"
                      formControlName="nome"
                      required>
              </mat-form-field>
              <div *ngIf="loginForm.get('nome').invalid && loginForm.get('nome').errors &&
                      (loginForm.get('nome').dirty || loginForm.get('nome').touched)">
                  <small class="text-danger"
                      *ngIf="loginForm.get('nome').hasError('required')">
                      O campo é obrigatório.
                  </small>
                  <small class="text-danger"
                      *ngIf="loginForm.get('nome').hasError('pattern')">
                      O campo precisa ter no mínimo 3 caracteres.
                  </small>
              </div>
              <br />
              <button mat-flat-button color="primary" class="login-btn" (click)="login()" [disabled]="(!this.loginForm.valid)">Entrar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .text-danger {
      font-size: 0.7rem;
      color: var(--danger);
      font-weight: 500;
      margin-top: -15px;
      position: absolute;
    }
    input.mat-input-element {
      margin-left: 10px;
    }
    .full-width {
      width: 100%;
      font-weight: 500;
    }
    .flex-container {
      height: 100%;
      padding: 0;
      margin: 0;
      display: -webkit-box;
      display: -moz-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .card {
      position: relative;
      display: -ms-flexbox;
      display: flex;
      -ms-flex-direction: column;
      flex-direction: column;
      width: 450px;
      word-wrap: break-word;
      background-color: #fff;
      background-clip: border-box;
      border: 1px solid rgba(0,0,0,.125);
      border-radius: .25rem;
    }
    .card-body {
      -ms-flex: 1 1 auto;
      flex: 1 1 auto;
      min-height: 1px;
      padding: 1.25rem;
    }
    .text-center {
      text-align: center!important;
    }
    .card-title {
      display: flex;
      justify-content: center;
      align-items: center;
      margin-bottom: .75rem;
      margin-top: .75rem;
    }
    .card-title img {
      width: 200px;
    }
    .form-group {
      margin-bottom: 20px;
    }
    .lost-pass {
      display: flex;
      justify-content: flex-end;
    }
    .login-btn {
      box-shadow: 0 3px 5px 0 rgb(47 85 212 / 30%);
      background: rgb(34,24,55);
      background: linear-gradient(155deg, rgba(34,24,55,1) 50%, rgba(119,37,61,1) 93%, rgba(110,24,32,1) 100%);
      padding: 8px 20px;
      width: 100%;
      font-weight: 300;
      font-size: 20px;
    }
  `]
})
export class LoginComponent {

  loginForm = new FormGroup({
    email: new FormControl(''),
    nome: new FormControl(''),
  });

  constructor(
    private apiService: ApiService,
    private router: Router
  ) { }

  login() {
    if (this.loginForm.valid) {
      this.apiService.login(this.loginForm.value.email, this.loginForm.value.nome)
        .subscribe((usuario: Usuario) => {
          console.log(usuario);
          localStorage.removeItem('usuario');
          localStorage.setItem('usuario', JSON.stringify(usuario));
          this.router.navigate(["/v1/veiculo"]);
        });
    }
  }
}
