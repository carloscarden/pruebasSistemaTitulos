import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AlumnoEstabOfertaDetailComponent } from './alumno-estab-oferta-detail.component';

describe('AlumnoEstabOferta Management Detail Component', () => {
  let comp: AlumnoEstabOfertaDetailComponent;
  let fixture: ComponentFixture<AlumnoEstabOfertaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AlumnoEstabOfertaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ alumnoEstabOferta: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AlumnoEstabOfertaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AlumnoEstabOfertaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load alumnoEstabOferta on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.alumnoEstabOferta).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
