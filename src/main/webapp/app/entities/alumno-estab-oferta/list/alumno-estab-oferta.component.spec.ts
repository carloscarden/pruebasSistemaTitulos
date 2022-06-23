import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { AlumnoEstabOfertaService } from '../service/alumno-estab-oferta.service';

import { AlumnoEstabOfertaComponent } from './alumno-estab-oferta.component';

describe('AlumnoEstabOferta Management Component', () => {
  let comp: AlumnoEstabOfertaComponent;
  let fixture: ComponentFixture<AlumnoEstabOfertaComponent>;
  let service: AlumnoEstabOfertaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [AlumnoEstabOfertaComponent],
    })
      .overrideTemplate(AlumnoEstabOfertaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AlumnoEstabOfertaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AlumnoEstabOfertaService);

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
    expect(comp.alumnoEstabOfertas?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
