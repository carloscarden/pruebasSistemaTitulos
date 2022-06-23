import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RendicionService } from '../service/rendicion.service';
import { IRendicion, Rendicion } from '../rendicion.model';

import { RendicionUpdateComponent } from './rendicion-update.component';

describe('Rendicion Management Update Component', () => {
  let comp: RendicionUpdateComponent;
  let fixture: ComponentFixture<RendicionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let rendicionService: RendicionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RendicionUpdateComponent],
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
      .overrideTemplate(RendicionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RendicionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    rendicionService = TestBed.inject(RendicionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const rendicion: IRendicion = { id: 456 };

      activatedRoute.data = of({ rendicion });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(rendicion));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Rendicion>>();
      const rendicion = { id: 123 };
      jest.spyOn(rendicionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rendicion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rendicion }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(rendicionService.update).toHaveBeenCalledWith(rendicion);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Rendicion>>();
      const rendicion = new Rendicion();
      jest.spyOn(rendicionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rendicion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rendicion }));
      saveSubject.complete();

      // THEN
      expect(rendicionService.create).toHaveBeenCalledWith(rendicion);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Rendicion>>();
      const rendicion = { id: 123 };
      jest.spyOn(rendicionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rendicion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(rendicionService.update).toHaveBeenCalledWith(rendicion);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
