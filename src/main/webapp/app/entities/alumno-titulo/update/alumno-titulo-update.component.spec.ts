import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AlumnoTituloService } from '../service/alumno-titulo.service';
import { IAlumnoTitulo, AlumnoTitulo } from '../alumno-titulo.model';
import { IAlumnoEstabOferta } from 'app/entities/alumno-estab-oferta/alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from 'app/entities/alumno-estab-oferta/service/alumno-estab-oferta.service';

import { AlumnoTituloUpdateComponent } from './alumno-titulo-update.component';

describe('AlumnoTitulo Management Update Component', () => {
  let comp: AlumnoTituloUpdateComponent;
  let fixture: ComponentFixture<AlumnoTituloUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let alumnoTituloService: AlumnoTituloService;
  let alumnoEstabOfertaService: AlumnoEstabOfertaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AlumnoTituloUpdateComponent],
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
      .overrideTemplate(AlumnoTituloUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AlumnoTituloUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    alumnoTituloService = TestBed.inject(AlumnoTituloService);
    alumnoEstabOfertaService = TestBed.inject(AlumnoEstabOfertaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call AlumnoEstabOferta query and add missing value', () => {
      const alumnoTitulo: IAlumnoTitulo = { id: 456 };
      const alumnoEstabOferta: IAlumnoEstabOferta = { id: 21054 };
      alumnoTitulo.alumnoEstabOferta = alumnoEstabOferta;

      const alumnoEstabOfertaCollection: IAlumnoEstabOferta[] = [{ id: 62327 }];
      jest.spyOn(alumnoEstabOfertaService, 'query').mockReturnValue(of(new HttpResponse({ body: alumnoEstabOfertaCollection })));
      const additionalAlumnoEstabOfertas = [alumnoEstabOferta];
      const expectedCollection: IAlumnoEstabOferta[] = [...additionalAlumnoEstabOfertas, ...alumnoEstabOfertaCollection];
      jest.spyOn(alumnoEstabOfertaService, 'addAlumnoEstabOfertaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ alumnoTitulo });
      comp.ngOnInit();

      expect(alumnoEstabOfertaService.query).toHaveBeenCalled();
      expect(alumnoEstabOfertaService.addAlumnoEstabOfertaToCollectionIfMissing).toHaveBeenCalledWith(
        alumnoEstabOfertaCollection,
        ...additionalAlumnoEstabOfertas
      );
      expect(comp.alumnoEstabOfertasSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const alumnoTitulo: IAlumnoTitulo = { id: 456 };
      const alumnoEstabOferta: IAlumnoEstabOferta = { id: 48820 };
      alumnoTitulo.alumnoEstabOferta = alumnoEstabOferta;

      activatedRoute.data = of({ alumnoTitulo });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(alumnoTitulo));
      expect(comp.alumnoEstabOfertasSharedCollection).toContain(alumnoEstabOferta);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoTitulo>>();
      const alumnoTitulo = { id: 123 };
      jest.spyOn(alumnoTituloService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoTitulo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: alumnoTitulo }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(alumnoTituloService.update).toHaveBeenCalledWith(alumnoTitulo);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoTitulo>>();
      const alumnoTitulo = new AlumnoTitulo();
      jest.spyOn(alumnoTituloService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoTitulo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: alumnoTitulo }));
      saveSubject.complete();

      // THEN
      expect(alumnoTituloService.create).toHaveBeenCalledWith(alumnoTitulo);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoTitulo>>();
      const alumnoTitulo = { id: 123 };
      jest.spyOn(alumnoTituloService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoTitulo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(alumnoTituloService.update).toHaveBeenCalledWith(alumnoTitulo);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackAlumnoEstabOfertaById', () => {
      it('Should return tracked AlumnoEstabOferta primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackAlumnoEstabOfertaById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
