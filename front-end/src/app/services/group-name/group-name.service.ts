import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiPath } from '../../enums/ApiPath';

@Injectable({
  providedIn: 'root'
})
export class GroupNameService {

  constructor(private http: HttpClient) {}



}
