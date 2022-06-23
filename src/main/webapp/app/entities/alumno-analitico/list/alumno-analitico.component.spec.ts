import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { AlumnoAnaliticoService } from '../service/alumno-analitico.service';

import { AlumnoAnaliticoComponent } from './alumno-analitico.component';

describe('AlumnoAnalitico Management Component', () => {
  let comp: AlumnoAnaliticoComponent;
  let fixture: ComponentFixture<AlumnoAnaliticoComponent>;
  let service: AlumnoAnaliticoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [AlumnoAnaliticoComponent],
    })
      .overrideTemplate(AlumnoAnaliticoComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AlumnoAnaliticoComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AlumnoAnaliticoService);

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
    expect(comp.alumnoAnaliticos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
