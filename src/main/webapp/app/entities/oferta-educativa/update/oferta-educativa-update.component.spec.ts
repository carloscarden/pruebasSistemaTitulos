import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { OfertaEducativaService } from '../service/oferta-educativa.service';
import { IOfertaEducativa, OfertaEducativa } from '../oferta-educativa.model';

import { OfertaEducativaUpdateComponent } from './oferta-educativa-update.component';

describe('OfertaEducativa Management Update Component', () => {
  let comp: OfertaEducativaUpdateComponent;
  let fixture: ComponentFixture<OfertaEducativaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ofertaEducativaService: OfertaEducativaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [OfertaEducativaUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(OfertaEducativaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OfertaEducativaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ofertaEducativaService = TestBed.inject(OfertaEducativaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ofertaEducativa: IOfertaEducativa = { id: 456 };

      activatedRoute.data = of({ ofertaEducativa });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(ofertaEducativa));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<OfertaEducativa>>();
      const ofertaEducativa = { id: 123 };
      jest.spyOn(ofertaEducativaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ofertaEducativa });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ofertaEducativa }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(ofertaEducativaService.update).toHaveBeenCalledWith(ofertaEducativa);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<OfertaEducativa>>();
      const ofertaEducativa = new OfertaEducativa();
      jest.spyOn(ofertaEducativaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ofertaEducativa });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ofertaEducativa }));
      saveSubject.complete();

      // THEN
      expect(ofertaEducativaService.create).toHaveBeenCalledWith(ofertaEducativa);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<OfertaEducativa>>();
      const ofertaEducativa = { id: 123 };
      jest.spyOn(ofertaEducativaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ofertaEducativa });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ofertaEducativaService.update).toHaveBeenCalledWith(ofertaEducativa);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
