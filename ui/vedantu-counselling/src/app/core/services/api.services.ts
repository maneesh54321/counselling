import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  public baseUrl: string = '';
  public webUrl: string = ''; 

  httpHeaders = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set("Accept", "application/json");

  constructor(private http: HttpClient) { }
  
  public downloadFile(relativeUrl: string): Observable<any> {
    return this.http.get(this.baseUrl + 'api/' + relativeUrl, { headers: this.httpHeaders, responseType: 'blob' });
  }
  public get(relativeUrl: string): Observable<any> {
    return this.http.get(this.baseUrl + 'api/' + relativeUrl, { headers: this.httpHeaders, responseType: 'json' });
  }

  public post(relativeUrl: string, data: any): Observable<any> {
    return this.http.post(this.baseUrl + 'api/' + relativeUrl, data, { headers: this.httpHeaders, responseType: 'json' });
  }

  public post4File(relativeUrl: string, data: any): Observable<any> {
    return this.http.post(this.baseUrl + 'api/' + relativeUrl, data, { headers: this.httpHeaders, responseType: 'blob' as 'json' });
  }

  public put(relativeUrl: string, data: any): Observable<any> {
    return this.http.put(this.baseUrl + 'api/' + relativeUrl, data, { headers: this.httpHeaders, responseType: 'json' });
  }

  public delete(relativeUrl: string): Observable<any> {
    return this.http.delete(this.baseUrl + 'api/' + relativeUrl, { headers: this.httpHeaders, responseType: 'json' });
  }

  public uploadFiles(relativeUrl: string, data: any): Observable<any> {
    let httpFilesHeaders = new HttpHeaders();
    return this.http.post(this.baseUrl + 'api/' + relativeUrl, data, { headers: httpFilesHeaders, responseType: 'json' });
  }

}
