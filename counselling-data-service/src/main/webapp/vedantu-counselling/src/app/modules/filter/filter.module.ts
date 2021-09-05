import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilterContainerComponent } from './filter-container/filter-container.component';
import { CategoryFilterComponent } from './category-filter/category-filter.component';
import { GenderFilterComponent } from './gender-filter/gender-filter.component';
import { QuotaFilterComponent } from './quota-filter/quota-filter.component';
import { SelectDropDownModule } from 'ngx-select-dropdown';



@NgModule({
  declarations: [
    FilterContainerComponent, 
    CategoryFilterComponent, 
    GenderFilterComponent, 
    QuotaFilterComponent,
  ],
  imports: [
    CommonModule,
    SelectDropDownModule,
  ],
  exports: [
    FilterContainerComponent, 
    CategoryFilterComponent, 
    GenderFilterComponent, 
    QuotaFilterComponent,
  ]
})
export class FilterModule { }
