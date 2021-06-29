import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';

import { CardComponent } from './card.component';
import { MaterialModule } from '../../../material.module';

@NgModule({
  imports: [
    RouterModule,
    CommonModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  exports: [
    CardComponent
  ],
  entryComponents: [
    CardComponent
  ],
  declarations: [
    CardComponent
  ],
  providers: [ ],
})
export class CardModule { }
