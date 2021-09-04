import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RankingRoutingModule } from './ranking-routing.module';
import { RankingDashboardComponent } from './ranking-dashboard/ranking-dashboard.component';
import { FilterModule } from './../filter/filter.module';

@NgModule({
  declarations: [
    RankingDashboardComponent,
  ],
  imports: [
    CommonModule,
    RankingRoutingModule,
    FilterModule,
  ]
})
export class RankingModule { }
