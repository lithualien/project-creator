import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Project } from 'src/app/models/project.model';

@Component({
  selector: 'app-project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.css']
})
export class ProjectCardComponent implements OnInit {

  @Input('project')
  public project!: Project;

  @Input('index')
  public index!: number;

  @Output('updateIndex')
  public updateIndex: EventEmitter<number> = new EventEmitter();

  @Output('deleteIndex')
  public deleteIndex: EventEmitter<number> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  public onClickUpdate(index: number): void {
    this.updateIndex.emit(index);
  }

  public onClickDelete(index: number): void {
    this.deleteIndex.emit(index);
  }

}
