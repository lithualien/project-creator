import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProjectComponent } from './components/project/project.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProjectCardComponent } from './components/project/project-card/project-card.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProjectModalCreateComponent } from './components/project/project-modal-create/project-modal-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SingleProjectComponent } from './components/project/single-project/single-project.component';
import { GroupComponent } from './components/group/group.component';
import { StudentComponent } from './components/student/student.component';

@NgModule({
  declarations: [
    AppComponent,
    ProjectComponent,
    NavbarComponent,
    ProjectCardComponent,
    ProjectModalCreateComponent,
    SingleProjectComponent,
    GroupComponent,
    StudentComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
