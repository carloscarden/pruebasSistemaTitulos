import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OfertaEducativaDetailComponent } from './oferta-educativa-detail.component';

describe('OfertaEducativa Management Detail Component', () => {
  let comp: OfertaEducativaDetailComponent;
  let fixture: ComponentFixture<OfertaEducativaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OfertaEducativaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ ofertaEducativa: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(OfertaEducativaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OfertaEducativaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ofertaEducativa on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.ofertaEducativa).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
