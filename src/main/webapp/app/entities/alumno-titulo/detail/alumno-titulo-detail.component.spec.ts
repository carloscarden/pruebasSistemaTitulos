import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AlumnoTituloDetailComponent } from './alumno-titulo-detail.component';

describe('AlumnoTitulo Management Detail Component', () => {
  let comp: AlumnoTituloDetailComponent;
  let fixture: ComponentFixture<AlumnoTituloDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AlumnoTituloDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ alumnoTitulo: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AlumnoTituloDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AlumnoTituloDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load alumnoTitulo on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.alumnoTitulo).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
