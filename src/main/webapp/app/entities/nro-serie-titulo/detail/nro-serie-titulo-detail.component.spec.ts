import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NroSerieTituloDetailComponent } from './nro-serie-titulo-detail.component';

describe('NroSerieTitulo Management Detail Component', () => {
  let comp: NroSerieTituloDetailComponent;
  let fixture: ComponentFixture<NroSerieTituloDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NroSerieTituloDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ nroSerieTitulo: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(NroSerieTituloDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(NroSerieTituloDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load nroSerieTitulo on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.nroSerieTitulo).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
