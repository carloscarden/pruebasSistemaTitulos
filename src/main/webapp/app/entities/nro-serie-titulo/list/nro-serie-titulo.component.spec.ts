import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { NroSerieTituloService } from '../service/nro-serie-titulo.service';

import { NroSerieTituloComponent } from './nro-serie-titulo.component';

describe('NroSerieTitulo Management Component', () => {
  let comp: NroSerieTituloComponent;
  let fixture: ComponentFixture<NroSerieTituloComponent>;
  let service: NroSerieTituloService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [NroSerieTituloComponent],
    })
      .overrideTemplate(NroSerieTituloComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NroSerieTituloComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(NroSerieTituloService);

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
    expect(comp.nroSerieTitulos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
