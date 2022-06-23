import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JornadaDetailComponent } from './jornada-detail.component';

describe('Jornada Management Detail Component', () => {
  let comp: JornadaDetailComponent;
  let fixture: ComponentFixture<JornadaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JornadaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ jornada: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(JornadaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(JornadaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load jornada on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.jornada).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
