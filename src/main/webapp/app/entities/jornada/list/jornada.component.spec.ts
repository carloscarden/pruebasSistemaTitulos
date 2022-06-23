import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { JornadaService } from '../service/jornada.service';

import { JornadaComponent } from './jornada.component';

describe('Jornada Management Component', () => {
  let comp: JornadaComponent;
  let fixture: ComponentFixture<JornadaComponent>;
  let service: JornadaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [JornadaComponent],
    })
      .overrideTemplate(JornadaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(JornadaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(JornadaService);

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
    expect(comp.jornadas?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
