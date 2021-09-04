import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RankingDashboardComponent } from './ranking-dashboard.component';

describe('RankingDashboardComponent', () => {
  let component: RankingDashboardComponent;
  let fixture: ComponentFixture<RankingDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RankingDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RankingDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
