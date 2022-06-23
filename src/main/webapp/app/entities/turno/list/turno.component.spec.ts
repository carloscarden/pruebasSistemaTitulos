import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TurnoService } from '../service/turno.service';

import { TurnoComponent } from './turno.component';

describe('Turno Management Component', () => {
  let comp: TurnoComponent;
  let fixture: ComponentFixture<TurnoComponent>;
  let service: TurnoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [TurnoComponent],
    })
      .overrideTemplate(TurnoComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TurnoComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TurnoService);

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
    expect(comp.turnos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
