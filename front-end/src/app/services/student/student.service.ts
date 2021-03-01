import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ApiPath } from 'src/app/enums/ApiPath';
import { Observable } from 'rxjs';
import { Student } from 'src/app/models/student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  public getStudentArray(): Observable<Array<Student>> {
    let url: string = `${this.baseUrl}${ApiPath.Student}`;
    return this.http.get<Array<Student>>(url);
  }

  public postStudent(student: Student): Observable<Student> { 
    let url: string = `${this.baseUrl}${ApiPath.Student}`;
    return this.http.post<Student>(url, student);
  }

  public deleteStudent(id: number): Observable<any> {
    let url: string = `${this.baseUrl}${ApiPath.Student}/${id}`;
    return this.http.delete<any>(url);
  }

  public postStudentGroup(studentId: number, groupId: number): Observable<any> {
    let url: string = `${this.baseUrl}${ApiPath.Student}/${studentId}/groups/${groupId}`;
    return this.http.post<any>(url, null);
  }

}
