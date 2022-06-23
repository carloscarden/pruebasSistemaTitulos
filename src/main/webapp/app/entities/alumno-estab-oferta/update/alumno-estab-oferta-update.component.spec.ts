import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AlumnoEstabOfertaService } from '../service/alumno-estab-oferta.service';
import { IAlumnoEstabOferta, AlumnoEstabOferta } from '../alumno-estab-oferta.model';
import { IAlumno } from 'app/entities/alumno/alumno.model';
import { AlumnoService } from 'app/entities/alumno/service/alumno.service';
import { IOfertaEducativa } from 'app/entities/oferta-educativa/oferta-educativa.model';
import { OfertaEducativaService } from 'app/entities/oferta-educativa/service/oferta-educativa.service';

import { AlumnoEstabOfertaUpdateComponent } from './alumno-estab-oferta-update.component';

describe('AlumnoEstabOferta Management Update Component', () => {
  let comp: AlumnoEstabOfertaUpdateComponent;
  let fixture: ComponentFixture<AlumnoEstabOfertaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let alumnoEstabOfertaService: AlumnoEstabOfertaService;
  let alumnoService: AlumnoService;
  let ofertaEducativaService: OfertaEducativaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AlumnoEstabOfertaUpdateComponent],
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
      .overrideTemplate(AlumnoEstabOfertaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AlumnoEstabOfertaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    alumnoEstabOfertaService = TestBed.inject(AlumnoEstabOfertaService);
    alumnoService = TestBed.inject(AlumnoService);
    ofertaEducativaService = TestBed.inject(OfertaEducativaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Alumno query and add missing value', () => {
      const alumnoEstabOferta: IAlumnoEstabOferta = { id: 456 };
      const alumno: IAlumno = { id: 53001 };
      alumnoEstabOferta.alumno = alumno;

      const alumnoCollection: IAlumno[] = [{ id: 43330 }];
      jest.spyOn(alumnoService, 'query').mockReturnValue(of(new HttpResponse({ body: alumnoCollection })));
      const additionalAlumnos = [alumno];
      const expectedCollection: IAlumno[] = [...additionalAlumnos, ...alumnoCollection];
      jest.spyOn(alumnoService, 'addAlumnoToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ alumnoEstabOferta });
      comp.ngOnInit();

      expect(alumnoService.query).toHaveBeenCalled();
      expect(alumnoService.addAlumnoToCollectionIfMissing).toHaveBeenCalledWith(alumnoCollection, ...additionalAlumnos);
      expect(comp.alumnosSharedCollection).toEqual(expectedCollection);
    });

    it('Should call OfertaEducativa query and add missing value', () => {
      const alumnoEstabOferta: IAlumnoEstabOferta = { id: 456 };
      const ofertaEducativa: IOfertaEducativa = { id: 86344 };
      alumnoEstabOferta.ofertaEducativa = ofertaEducativa;

      const ofertaEducativaCollection: IOfertaEducativa[] = [{ id: 13004 }];
      jest.spyOn(ofertaEducativaService, 'query').mockReturnValue(of(new HttpResponse({ body: ofertaEducativaCollection })));
      const additionalOfertaEducativas = [ofertaEducativa];
      const expectedCollection: IOfertaEducativa[] = [...additionalOfertaEducativas, ...ofertaEducativaCollection];
      jest.spyOn(ofertaEducativaService, 'addOfertaEducativaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ alumnoEstabOferta });
      comp.ngOnInit();

      expect(ofertaEducativaService.query).toHaveBeenCalled();
      expect(ofertaEducativaService.addOfertaEducativaToCollectionIfMissing).toHaveBeenCalledWith(
        ofertaEducativaCollection,
        ...additionalOfertaEducativas
      );
      expect(comp.ofertaEducativasSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const alumnoEstabOferta: IAlumnoEstabOferta = { id: 456 };
      const alumno: IAlumno = { id: 76182 };
      alumnoEstabOferta.alumno = alumno;
      const ofertaEducativa: IOfertaEducativa = { id: 44330 };
      alumnoEstabOferta.ofertaEducativa = ofertaEducativa;

      activatedRoute.data = of({ alumnoEstabOferta });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(alumnoEstabOferta));
      expect(comp.alumnosSharedCollection).toContain(alumno);
      expect(comp.ofertaEducativasSharedCollection).toContain(ofertaEducativa);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoEstabOferta>>();
      const alumnoEstabOferta = { id: 123 };
      jest.spyOn(alumnoEstabOfertaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoEstabOferta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: alumnoEstabOferta }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(alumnoEstabOfertaService.update).toHaveBeenCalledWith(alumnoEstabOferta);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoEstabOferta>>();
      const alumnoEstabOferta = new AlumnoEstabOferta();
      jest.spyOn(alumnoEstabOfertaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoEstabOferta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: alumnoEstabOferta }));
      saveSubject.complete();

      // THEN
      expect(alumnoEstabOfertaService.create).toHaveBeenCalledWith(alumnoEstabOferta);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoEstabOferta>>();
      const alumnoEstabOferta = { id: 123 };
      jest.spyOn(alumnoEstabOfertaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoEstabOferta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(alumnoEstabOfertaService.update).toHaveBeenCalledWith(alumnoEstabOferta);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackAlumnoById', () => {
      it('Should return tracked Alumno primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackAlumnoById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackOfertaEducativaById', () => {
      it('Should return tracked OfertaEducativa primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackOfertaEducativaById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
