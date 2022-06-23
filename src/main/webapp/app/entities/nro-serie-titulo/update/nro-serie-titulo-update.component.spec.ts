import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { NroSerieTituloService } from '../service/nro-serie-titulo.service';
import { INroSerieTitulo, NroSerieTitulo } from '../nro-serie-titulo.model';

import { NroSerieTituloUpdateComponent } from './nro-serie-titulo-update.component';

describe('NroSerieTitulo Management Update Component', () => {
  let comp: NroSerieTituloUpdateComponent;
  let fixture: ComponentFixture<NroSerieTituloUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let nroSerieTituloService: NroSerieTituloService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [NroSerieTituloUpdateComponent],
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
      .overrideTemplate(NroSerieTituloUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NroSerieTituloUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    nroSerieTituloService = TestBed.inject(NroSerieTituloService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const nroSerieTitulo: INroSerieTitulo = { id: 456 };

      activatedRoute.data = of({ nroSerieTitulo });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(nroSerieTitulo));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NroSerieTitulo>>();
      const nroSerieTitulo = { id: 123 };
      jest.spyOn(nroSerieTituloService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nroSerieTitulo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: nroSerieTitulo }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(nroSerieTituloService.update).toHaveBeenCalledWith(nroSerieTitulo);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NroSerieTitulo>>();
      const nroSerieTitulo = new NroSerieTitulo();
      jest.spyOn(nroSerieTituloService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nroSerieTitulo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: nroSerieTitulo }));
      saveSubject.complete();

      // THEN
      expect(nroSerieTituloService.create).toHaveBeenCalledWith(nroSerieTitulo);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NroSerieTitulo>>();
      const nroSerieTitulo = { id: 123 };
      jest.spyOn(nroSerieTituloService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nroSerieTitulo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(nroSerieTituloService.update).toHaveBeenCalledWith(nroSerieTitulo);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
