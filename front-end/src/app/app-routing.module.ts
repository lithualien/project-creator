import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectComponent } from './components/project/project.component';
import { SingleProjectComponent } from './components/project/single-project/single-project.component';
import { StudentComponent } from './components/student/student.component';

const routes: Routes = [
  { path: '', component: ProjectComponent },
  { path: 'projects/:id', component: SingleProjectComponent },
  { path: 'students', component: StudentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
