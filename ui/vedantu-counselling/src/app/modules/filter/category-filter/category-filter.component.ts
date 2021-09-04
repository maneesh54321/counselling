import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ApiService } from '../../../core/services/api.services';
import { categoryFilter, NameConstant } from '../../../core/constant/name-constant';
@Component({
  selector: 'app-category-filter',
  templateUrl: './category-filter.component.html',
  styleUrls: ['./category-filter.component.css']
})
export class CategoryFilterComponent implements OnInit, OnDestroy {
  componentName: NameConstant = categoryFilter;

  config = {
    displayKey:"value", //if objects array passed which key to be displayed defaults to description
    search:true, //true/false for the search functionlity defaults to false,
    height: 'auto', //height of the list so that if there are more no of items it can show a scroll defaults to auto. With auto height scroll will never appear
    placeholder:'Select '+ this.componentName.name, // text to be displayed when no item is selected defaults to Select,
    customComparator: ()=>{}, // a custom function using which user wants to sort the items. default is undefined and Array.sort() will be used in that case,
    limitTo: 0, // number thats limits the no of options displayed in the UI (if zero, options will not be limited)
    moreText: 'more', // text to be displayed whenmore than one items are selected like Option 1 + 5 more
    noResultsFound: 'No results found!', // text to be displayed when no items are found while searching
    searchPlaceholder:'Search', // label thats displayed in search input,
    searchOnKey: 'value', // key on which search should be performed this will be selective search. if undefined this will be extensive search on all keys
    clearOnSelection: false, // clears search criteria when an option is selected if set to true, default is false
    inputDirection: 'ltr' // the direction of the search input can be rtl or ltr(default)
  }

  dropdownOptions;
  dropdownOptionSub: Subscription;
  categoryDropdownControl = new FormControl('');
  constructor(
    private apiService: ApiService
  ) { }

  ngOnInit(): void {
    this.loadDropDownOptions();
  }

  ngOnDestroy(){
    if(this.dropdownOptionSub){
      this.dropdownOptionSub.unsubscribe();
    }
  }
  loadDropDownOptions(){
    this.dropdownOptionSub = this.apiService.get("mockcategory").subscribe( res => {
      this.dropdownOptions = res.data;
    });
  }

  selectionChanged($event){

  }
  
}
