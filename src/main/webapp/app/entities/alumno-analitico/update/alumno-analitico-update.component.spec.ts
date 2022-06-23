import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AlumnoAnaliticoService } from '../service/alumno-analitico.service';
import { IAlumnoAnalitico, AlumnoAnalitico } from '../alumno-analitico.model';
import { IAlumnoEstabOferta } from 'app/entities/alumno-estab-oferta/alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from 'app/entities/alumno-estab-oferta/service/alumno-estab-oferta.service';
import { IMateria } from 'app/entities/materia/materia.model';
import { MateriaService } from 'app/entities/materia/service/materia.service';

import { AlumnoAnaliticoUpdateComponent } from './alumno-analitico-update.component';

describe('AlumnoAnalitico Management Update Component', () => {
  let comp: AlumnoAnaliticoUpdateComponent;
  let fixture: ComponentFixture<AlumnoAnaliticoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let alumnoAnaliticoService: AlumnoAnaliticoService;
  let alumnoEstabOfertaService: AlumnoEstabOfertaService;
  let materiaService: MateriaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AlumnoAnaliticoUpdateComponent],
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
      .overrideTemplate(AlumnoAnaliticoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AlumnoAnaliticoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    alumnoAnaliticoService = TestBed.inject(AlumnoAnaliticoService);
    alumnoEstabOfertaService = TestBed.inject(AlumnoEstabOfertaService);
    materiaService = TestBed.inject(MateriaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call AlumnoEstabOferta query and add missing value', () => {
      const alumnoAnalitico: IAlumnoAnalitico = { id: 456 };
      const alumnoEstabOferta: IAlumnoEstabOferta = { id: 31484 };
      alumnoAnalitico.alumnoEstabOferta = alumnoEstabOferta;

      const alumnoEstabOfertaCollection: IAlumnoEstabOferta[] = [{ id: 4177 }];
      jest.spyOn(alumnoEstabOfertaService, 'query').mockReturnValue(of(new HttpResponse({ body: alumnoEstabOfertaCollection })));
      const additionalAlumnoEstabOfertas = [alumnoEstabOferta];
      const expectedCollection: IAlumnoEstabOferta[] = [...additionalAlumnoEstabOfertas, ...alumnoEstabOfertaCollection];
      jest.spyOn(alumnoEstabOfertaService, 'addAlumnoEstabOfertaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ alumnoAnalitico });
      comp.ngOnInit();

      expect(alumnoEstabOfertaService.query).toHaveBeenCalled();
      expect(alumnoEstabOfertaService.addAlumnoEstabOfertaToCollectionIfMissing).toHaveBeenCalledWith(
        alumnoEstabOfertaCollection,
        ...additionalAlumnoEstabOfertas
      );
      expect(comp.alumnoEstabOfertasSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Materia query and add missing value', () => {
      const alumnoAnalitico: IAlumnoAnalitico = { id: 456 };
      const materia: IMateria = { id: 23021 };
      alumnoAnalitico.materia = materia;

      const materiaCollection: IMateria[] = [{ id: 76505 }];
      jest.spyOn(materiaService, 'query').mockReturnValue(of(new HttpResponse({ body: materiaCollection })));
      const additionalMaterias = [materia];
      const expectedCollection: IMateria[] = [...additionalMaterias, ...materiaCollection];
      jest.spyOn(materiaService, 'addMateriaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ alumnoAnalitico });
      comp.ngOnInit();

      expect(materiaService.query).toHaveBeenCalled();
      expect(materiaService.addMateriaToCollectionIfMissing).toHaveBeenCalledWith(materiaCollection, ...additionalMaterias);
      expect(comp.materiasSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const alumnoAnalitico: IAlumnoAnalitico = { id: 456 };
      const alumnoEstabOferta: IAlumnoEstabOferta = { id: 32248 };
      alumnoAnalitico.alumnoEstabOferta = alumnoEstabOferta;
      const materia: IMateria = { id: 57168 };
      alumnoAnalitico.materia = materia;

      activatedRoute.data = of({ alumnoAnalitico });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(alumnoAnalitico));
      expect(comp.alumnoEstabOfertasSharedCollection).toContain(alumnoEstabOferta);
      expect(comp.materiasSharedCollection).toContain(materia);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoAnalitico>>();
      const alumnoAnalitico = { id: 123 };
      jest.spyOn(alumnoAnaliticoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoAnalitico });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: alumnoAnalitico }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(alumnoAnaliticoService.update).toHaveBeenCalledWith(alumnoAnalitico);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoAnalitico>>();
      const alumnoAnalitico = new AlumnoAnalitico();
      jest.spyOn(alumnoAnaliticoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoAnalitico });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: alumnoAnalitico }));
      saveSubject.complete();

      // THEN
      expect(alumnoAnaliticoService.create).toHaveBeenCalledWith(alumnoAnalitico);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<AlumnoAnalitico>>();
      const alumnoAnalitico = { id: 123 };
      jest.spyOn(alumnoAnaliticoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ alumnoAnalitico });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(alumnoAnaliticoService.update).toHaveBeenCalledWith(alumnoAnalitico);
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

    describe('trackMateriaById', () => {
      it('Should return tracked Materia primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackMateriaById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
