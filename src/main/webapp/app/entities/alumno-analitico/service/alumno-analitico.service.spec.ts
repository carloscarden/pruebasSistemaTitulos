import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAlumnoAnalitico, AlumnoAnalitico } from '../alumno-analitico.model';

import { AlumnoAnaliticoService } from './alumno-analitico.service';

describe('AlumnoAnalitico Service', () => {
  let service: AlumnoAnaliticoService;
  let httpMock: HttpTestingController;
  let elemDefault: IAlumnoAnalitico;
  let expectedResult: IAlumnoAnalitico | IAlumnoAnalitico[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AlumnoAnaliticoService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      nota: 0,
      idEstadoAlumnoAnalitico: 0,
      idAlumnoEstabOferta: 0,
      idMateria: 0,
      idMesImp: 0,
      idAnoImp: 0,
      establecimientoImp: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a AlumnoAnalitico', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new AlumnoAnalitico()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AlumnoAnalitico', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nota: 1,
          idEstadoAlumnoAnalitico: 1,
          idAlumnoEstabOferta: 1,
          idMateria: 1,
          idMesImp: 1,
          idAnoImp: 1,
          establecimientoImp: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AlumnoAnalitico', () => {
      const patchObject = Object.assign(
        {
          nota: 1,
          idEstadoAlumnoAnalitico: 1,
          idAnoImp: 1,
          establecimientoImp: 'BBBBBB',
        },
        new AlumnoAnalitico()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AlumnoAnalitico', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nota: 1,
          idEstadoAlumnoAnalitico: 1,
          idAlumnoEstabOferta: 1,
          idMateria: 1,
          idMesImp: 1,
          idAnoImp: 1,
          establecimientoImp: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a AlumnoAnalitico', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addAlumnoAnaliticoToCollectionIfMissing', () => {
      it('should add a AlumnoAnalitico to an empty array', () => {
        const alumnoAnalitico: IAlumnoAnalitico = { id: 123 };
        expectedResult = service.addAlumnoAnaliticoToCollectionIfMissing([], alumnoAnalitico);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(alumnoAnalitico);
      });

      it('should not add a AlumnoAnalitico to an array that contains it', () => {
        const alumnoAnalitico: IAlumnoAnalitico = { id: 123 };
        const alumnoAnaliticoCollection: IAlumnoAnalitico[] = [
          {
            ...alumnoAnalitico,
          },
          { id: 456 },
        ];
        expectedResult = service.addAlumnoAnaliticoToCollectionIfMissing(alumnoAnaliticoCollection, alumnoAnalitico);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AlumnoAnalitico to an array that doesn't contain it", () => {
        const alumnoAnalitico: IAlumnoAnalitico = { id: 123 };
        const alumnoAnaliticoCollection: IAlumnoAnalitico[] = [{ id: 456 }];
        expectedResult = service.addAlumnoAnaliticoToCollectionIfMissing(alumnoAnaliticoCollection, alumnoAnalitico);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(alumnoAnalitico);
      });

      it('should add only unique AlumnoAnalitico to an array', () => {
        const alumnoAnaliticoArray: IAlumnoAnalitico[] = [{ id: 123 }, { id: 456 }, { id: 95319 }];
        const alumnoAnaliticoCollection: IAlumnoAnalitico[] = [{ id: 123 }];
        expectedResult = service.addAlumnoAnaliticoToCollectionIfMissing(alumnoAnaliticoCollection, ...alumnoAnaliticoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const alumnoAnalitico: IAlumnoAnalitico = { id: 123 };
        const alumnoAnalitico2: IAlumnoAnalitico = { id: 456 };
        expectedResult = service.addAlumnoAnaliticoToCollectionIfMissing([], alumnoAnalitico, alumnoAnalitico2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(alumnoAnalitico);
        expect(expectedResult).toContain(alumnoAnalitico2);
      });

      it('should accept null and undefined values', () => {
        const alumnoAnalitico: IAlumnoAnalitico = { id: 123 };
        expectedResult = service.addAlumnoAnaliticoToCollectionIfMissing([], null, alumnoAnalitico, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(alumnoAnalitico);
      });

      it('should return initial array if no AlumnoAnalitico is added', () => {
        const alumnoAnaliticoCollection: IAlumnoAnalitico[] = [{ id: 123 }];
        expectedResult = service.addAlumnoAnaliticoToCollectionIfMissing(alumnoAnaliticoCollection, undefined, null);
        expect(expectedResult).toEqual(alumnoAnaliticoCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
