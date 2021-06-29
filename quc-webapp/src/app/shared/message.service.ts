import { MatSnackBar, MAT_SNACK_BAR_DATA, MatSnackBarRef } from '@angular/material/snack-bar';
import { Injectable, Component, Inject } from '@angular/core';

@Injectable()
export class MessageService {

  constructor(private snackBar: MatSnackBar) { }

  public enviarMensagem(msg: string, btnMsg: string = "Ok") {
    msg = msg.toLowerCase(); 
    
    let msgFormated = msg.charAt(0).toUpperCase() + msg.slice(1);

    this.snackBar.openFromComponent(MessageSnackBarComponent, {
      duration: 30000,
      data: { msg: msgFormated, btnMsg: btnMsg }
    });
  }
}

@Component({
  selector: 'message-snack-bar-component',
  template: `
    <div class="flex">
      <span class="message">
        {{ data.msg }}
      </span>
      <button *ngIf="(data.btnMsg)" mat-button (click)="close()">{{ data.btnMsg }}</button>
    </div>
  `,
  styles: [`
    .flex {
      display: flex;
      align-items: center;
      justify-content: space-between;
    }
    .message {
      color: var(--light);
    }
  `],
})
export class MessageSnackBarComponent {

  constructor(
    public snackBarRef: MatSnackBarRef<MessageSnackBarComponent>,
    @Inject(MAT_SNACK_BAR_DATA) public data: any
  ){}

  close() {
    this.snackBarRef.dismiss();
  }
}
