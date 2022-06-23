import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RendicionDetailComponent } from './rendicion-detail.component';

describe('Rendicion Management Detail Component', () => {
  let comp: RendicionDetailComponent;
  let fixture: ComponentFixture<RendicionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RendicionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ rendicion: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(RendicionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RendicionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load rendicion on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.rendicion).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
