import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VeiculoComponent } from './veiculo/veiculo.component';
import { ReservaComponent } from './reserva/reserva.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'veiculo',
    pathMatch: 'full'
  },
  {
    path: 'veiculo',
    component: VeiculoComponent
  },
  {
    path: 'reserva/:idVeiculo',
    component: ReservaComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
