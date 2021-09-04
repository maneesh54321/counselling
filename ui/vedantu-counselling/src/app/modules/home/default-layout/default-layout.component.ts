import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-default-layout',
  templateUrl: './default-layout.component.html',
  styleUrls: ['./default-layout.component.css']
})
export class DefaultLayoutComponent implements OnInit {
  safeUrl;

  constructor(private _sanitizer: DomSanitizer){
    this.safeUrl = this._sanitizer.bypassSecurityTrustResourceUrl("https://www.youtube-nocookie.com/embed/WB-y7_ymPJ4");
 }
  ngOnInit(): void {
    console.log("Default Layout");
  }

}
