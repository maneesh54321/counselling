import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuotaFilterComponent } from './quota-filter.component';

describe('QuotaFilterComponent', () => {
  let component: QuotaFilterComponent;
  let fixture: ComponentFixture<QuotaFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuotaFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuotaFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
