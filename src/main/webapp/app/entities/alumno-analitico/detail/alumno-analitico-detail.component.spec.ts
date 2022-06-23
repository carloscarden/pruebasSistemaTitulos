import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AlumnoAnaliticoDetailComponent } from './alumno-analitico-detail.component';

describe('AlumnoAnalitico Management Detail Component', () => {
  let comp: AlumnoAnaliticoDetailComponent;
  let fixture: ComponentFixture<AlumnoAnaliticoDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AlumnoAnaliticoDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ alumnoAnalitico: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AlumnoAnaliticoDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AlumnoAnaliticoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load alumnoAnalitico on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.alumnoAnalitico).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
