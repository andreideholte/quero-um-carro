import { Component, Input } from '@angular/core';

@Component({
  selector: 'card',
  template: `
    <div class="upload">
        <div class="upload-files">
            <header>
                <p class="space">
                    <mat-icon class="pointer-none">{{this.headerCard?.icon}}</mat-icon>
                </p>
                <p>
                    <span class="up">{{this.headerCard?.titleBold}}</span>
                    <span class="load">{{this.headerCard?.titleRegular}}</span>
                </p>
            </header>
            <div id="drop" class="center">
                <ng-content></ng-content>
            </div>
        </div>
    </div>
  `,
  styles: [`
    :host {
        display: flex;
        justify-content: center;
        align-content: center;
    }
    .center {
        height: 100%;
        justify-content: center;
        align-content: center;
    }
    .upload {
        position: relative;
        width: 100%;
        min-height: 330px;
        box-sizing: border-box;
        border-radius: 5px;
        box-shadow: 0 13px 15px 0 rgb(47 85 212 / 10%);
        padding-bottom: 20px;
        background: var(--white);
        -webkit-animation: fadeup .5s .5s ease both;
                animation: fadeup .5s .5s ease both;
        -webkit-transform: translateY(20px);
                transform: translateY(20px);
        opacity: 0;
    }
    .upload .upload-files header {
        background: var(--theme-detail-bg-color);
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
        text-align: center;
    }
    .upload .upload-files header p {
        color: var(--secondary);
        font-size: 20px;
        margin: 0;
    }
    .upload .upload-files header p mat-icon {
        padding: 50px 0;
        font-size: 65px !important;
        color: var(--secondary);
        margin-left: -40px;
        -webkit-transform: translateY(20px);
                transform: translateY(20px);
        opacity: 0;
        font-size: 30px;
        -webkit-animation: fadeup .5s 1s ease both;
                animation: fadeup .5s 1s ease both;
    }
    .upload .upload-files header p .up {
        font-weight: bold;
        margin-bottom: 40px;
        -webkit-transform: translateX(-20px);
                transform: translateX(-20px);
        display: inline-block;
        opacity: 0;
        -webkit-animation: faderight .5s 1.5s ease both;
                animation: faderight .5s 1.5s ease both;
    }
    .upload .upload-files header p .load {
        display: inline-block;
        font-weight: 100;
        margin-left: 7px;
        -webkit-transform: translateX(-20px);
                transform: translateX(-20px);
        opacity: 0;
        -webkit-animation: faderight 1s 1.5s ease both;
                animation: faderight 1s 1.5s ease both;
    }
    .upload .upload-files .center {
        padding: 50px 0;
        padding-bottom: 30px;
    }
    @-webkit-keyframes fadeup {
        to {
        -webkit-transform: translateY(0);
                transform: translateY(0);
        opacity: 1;
        }
    }
    @keyframes fadeup {
        to {
        -webkit-transform: translateY(0);
                transform: translateY(0);
        opacity: 1;
        }
    }
    @-webkit-keyframes faderight {
        to {
        -webkit-transform: translateX(0);
                transform: translateX(0);
        opacity: 1;
        }
    }
    @keyframes faderight {
        to {
        -webkit-transform: translateX(0);
                transform: translateX(0);
        opacity: 1;
        }
    }
  `],
 })
export class CardComponent {

  @Input() headerCard: HeaderCard;

  constructor() { }

}

export class HeaderCard {

  icon: string;
  titleBold: string;
  titleRegular: string;

  constructor(icon:string, titleBold:string, titleRegular:string){
      this.icon = icon;
      this.titleBold = titleBold;
      this.titleRegular = titleRegular;
  }
}
