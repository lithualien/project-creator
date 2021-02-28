import { HttpErrorResponse } from '@angular/common/http';
import { Component, ComponentFactoryResolver, EventEmitter, OnInit } from '@angular/core';
import { NgbModal, NgbModalConfig, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { NgbModalStack } from '@ng-bootstrap/ng-bootstrap/modal/modal-stack';
import { ApiError } from 'src/app/models/error.model';
import { Project } from 'src/app/models/project.model';
import { ProjectService } from 'src/app/services/project/project.service';
import { ProjectModalCreateComponent } from './project-modal-create/project-modal-create.component';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  private loading: boolean = false;
  private creatingInProgress: boolean = false;
  private created: boolean = false;
  private save: boolean = true;
  private projectArray: Array<Project> = [];


  constructor(
    private projectService: ProjectService,
    private ngbModal: NgbModal
  ) { }

  ngOnInit(): void {
    this.getProjects();
  }

  public getProjects() {
    this.setLoading(true);
    this.projectService
      .getProjects()
      .subscribe(result => {
        this.setLoading(false);
        this.projectArray = result;
      }, error => {
        this.setLoading(false);
      })
  }

  public getLoading(): boolean {
    return this.loading;
  }

  public setLoading(status: boolean): void {
    this.loading = status;
  }

  public getCreatingInProgress(): boolean {
    return this.creatingInProgress;
  }

  public setCreatingInProgress(value: boolean) {
    this.creatingInProgress = value;
  }

  public getCreated(): boolean {
    return this.created;
  }

  public setCreated(value: boolean) {
    this.created = value;
  }

  public getProjectArray(): Array<Project> {
    return this.projectArray;
  }

  public isProjectArrayEmpty(): boolean {
    return this.projectArray.length < 1;
  }

  public openCreateModal(): void {
    const modalRef: NgbModalRef = this.createModal();
    let defaultProject: Project = { id: 0, projectName: '', groupAmount: 1, studentsPerGroup: 2 }
    this.save = true;
    this.intialialModalRefConfiguration(modalRef, defaultProject);

    modalRef.componentInstance.submittedProject.subscribe((result: Project) => {

      if (result !== undefined) {
        this.setCreatingInProgress(true);
        modalRef.componentInstance.creatingInProgress = this.getCreatingInProgress();

        this.projectService.postProject(result).subscribe((newProject: Project) => {
          this.setOnGoodResult(modalRef, newProject);
          this.getProjectArray().push(newProject);
        },
          ((apiError: HttpErrorResponse) => {
            this.handleApiError(modalRef, apiError);
          }));
      }
    });

    modalRef.closed.subscribe(() => {
      this.resetModaRef(modalRef);
    })

  }

  public handleUpdateClick(index: number): void {
    const modalRef: NgbModalRef = this.createModal();
    let project: Project = this.projectArray[index];
    this.save = false;
    this.intialialModalRefConfiguration(modalRef, project);

    modalRef.componentInstance.submittedProject.subscribe((result: Project) => {

      if (result !== undefined) {
        this.setCreatingInProgress(true);
        modalRef.componentInstance.creatingInProgress = this.getCreatingInProgress();

        this.projectService.putProject(result, project.id).subscribe((newProject: Project) => {
          this.setOnGoodResult(modalRef, newProject);
          this.projectArray[index] = newProject;
        },
          ((apiError: HttpErrorResponse) => {
            this.handleApiError(modalRef, apiError);
          }));
      }
    });

    modalRef.closed.subscribe(() => {
      this.resetModaRef(modalRef);
    })

  }

  public handleDeleteClick(index: number): void {  
    const modalRef: NgbModalRef = this.createModal();
    let project: Project = this.projectArray[index];
    this.initializeForDelete(modalRef, project);

    modalRef.componentInstance.submittedProject.subscribe((result: Project) => {
      if(result !== undefined) {
        modalRef.componentInstance.deleteInProgress = true;
        this.projectService.deleteProject(result.id).subscribe(() => {
          this.projectArray.splice(index, 1);
          modalRef.componentInstance.deleteInProgress = false;
          modalRef.componentInstance.deleteSuccessful = true;
        },
        ((apiError: HttpErrorResponse) => {
          modalRef.componentInstance.deleteSuccessful = false;
        }));
      }
    });

    modalRef.closed.subscribe(() => {
      this.resetModaRef(modalRef);
    })

  }

  private createModal(): NgbModalRef {
    return this.ngbModal.open(ProjectModalCreateComponent, { centered: true, size: 'lg' });
  }

  private resetModaRef(modalRef: NgbModalRef): void {
    this.setCreated(false);
    this.setCreatingInProgress(false);

    modalRef.componentInstance.creatingInProgress = this.getCreatingInProgress();
    modalRef.componentInstance.created = this.getCreated();
    modalRef.componentInstance.newProject = null;
    modalRef.componentInstance.errorMessage = '';
    modalRef.componentInstance.delete = false;
    modalRef.componentInstance.deleteInProgress = false;
    modalRef.componentInstance.deleteSuccessful = false;
  }

  private setOnGoodResult(modalRef: NgbModalRef, newProject: Project): void {
    this.setCreatingInProgress(false);
    this.setCreated(true);

    modalRef.componentInstance.creatingInProgress = this.getCreatingInProgress();
    modalRef.componentInstance.created = this.getCreated();
    modalRef.componentInstance.newProject = newProject;
  }

  private handleApiError(modalRef: NgbModalRef, apiError: HttpErrorResponse): void {
    this.setCreatingInProgress(false);
    modalRef.componentInstance.creatingInProgress = this.getCreatingInProgress();
    if (apiError.error.status === 400) {
      modalRef.componentInstance.errorMessage = apiError.error.message;
    } else {
      modalRef.componentInstance.errorMessage = "Unexpected error occured.";
    }
  }

  private intialialModalRefConfiguration(modalRef: NgbModalRef, project: Project): void {
    modalRef.componentInstance.creatingInProgress = this.getCreatingInProgress();
    modalRef.componentInstance.project = project;
    modalRef.componentInstance.created = this.getCreated();
    modalRef.componentInstance.save = this.save;
  }

  private initializeForDelete(modalRef: NgbModalRef, project: Project): void {
    modalRef.componentInstance.creatingInProgress = false;
    modalRef.componentInstance.created = false;
    modalRef.componentInstance.errorMessage = '';
    modalRef.componentInstance.project = project;
    modalRef.componentInstance.newProject = null;
    modalRef.componentInstance.save = false;
    modalRef.componentInstance.delete = true;
  }

}
