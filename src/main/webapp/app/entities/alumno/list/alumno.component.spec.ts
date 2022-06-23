import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { AlumnoService } from '../service/alumno.service';

import { AlumnoComponent } from './alumno.component';

describe('Alumno Management Component', () => {
  let comp: AlumnoComponent;
  let fixture: ComponentFixture<AlumnoComponent>;
  let service: AlumnoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [AlumnoComponent],
    })
      .overrideTemplate(AlumnoComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AlumnoComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AlumnoService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.alumnos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
