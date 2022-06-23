import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { OfertaEducativaService } from '../service/oferta-educativa.service';

import { OfertaEducativaComponent } from './oferta-educativa.component';

describe('OfertaEducativa Management Component', () => {
  let comp: OfertaEducativaComponent;
  let fixture: ComponentFixture<OfertaEducativaComponent>;
  let service: OfertaEducativaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [OfertaEducativaComponent],
    })
      .overrideTemplate(OfertaEducativaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OfertaEducativaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OfertaEducativaService);

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
    expect(comp.ofertaEducativas?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
