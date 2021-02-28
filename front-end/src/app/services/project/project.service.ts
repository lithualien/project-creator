import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { ApiPath } from '../../enums/ApiPath';
import { ProjectGroup } from 'src/app/models/project-group.model';
import { Project } from 'src/app/models/project.model';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  public getProjects(): Observable<Array<Project>> {
    let url: string = `${this.baseUrl}${ApiPath.Project}`;
    return this.http.get<Array<Project>>(url);
  }

  public getProjectWithGroup(id: number): Observable<ProjectGroup> {
    let url: string = `${this.baseUrl}${ApiPath.Project}/${id}`;
    return this.http.get<ProjectGroup>(url);
  }

  public postProject(project: Project): Observable<Project> {
    let url: string = `${this.baseUrl}${ApiPath.Project}`;
    return this.http.post<Project>(url, project);
  }
  
  public putProject(project: Project, id: number): Observable<Project> {
    let url: string = `${this.baseUrl}${ApiPath.Project}/${id}`;
    return this.http.put<Project>(url, project);
  }

  public deleteProject(id: number): Observable<any> {
    let url: string = `${this.baseUrl}${ApiPath.Project}/${id}`;
    return this.http.delete<any>(url);
  }

}
