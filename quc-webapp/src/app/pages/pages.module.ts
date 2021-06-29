import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material.module';
import { MessageService, MessageSnackBarComponent } from '../shared/message.service';
import { VeiculoComponent } from './veiculo/veiculo.component';
import { ReservaComponent } from './reserva/reserva.component';
import { PagesRoutingModule } from './pages-routing.module';
import { CardModule } from '../shared/components/card/card.module';

@NgModule({
  declarations: [
    VeiculoComponent,
    ReservaComponent,
    MessageSnackBarComponent
  ],
  imports: [
    CommonModule,
    PagesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    FlexLayoutModule,
    CardModule
  ],
  providers: [
    MessageService
  ],
})
export class PagesModule { }
