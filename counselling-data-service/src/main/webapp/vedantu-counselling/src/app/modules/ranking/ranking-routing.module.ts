import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RankingDashboardComponent } from './ranking-dashboard/ranking-dashboard.component';

const routes: Routes = [{
  path: '',
  children: [
    {
      path: '',
      redirectTo: '/dashboard',
      pathMatch: 'full'
    },
    {
      path: 'dashboard',
      component: RankingDashboardComponent
    }
  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RankingRoutingModule { }
