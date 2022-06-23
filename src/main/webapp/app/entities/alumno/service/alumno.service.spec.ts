import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAlumno, Alumno } from '../alumno.model';

import { AlumnoService } from './alumno.service';

describe('Alumno Service', () => {
  let service: AlumnoService;
  let httpMock: HttpTestingController;
  let elemDefault: IAlumno;
  let expectedResult: IAlumno | IAlumno[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AlumnoService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      documento: 'AAAAAAA',
      idTipoDocumento: 0,
      nombre: 'AAAAAAA',
      apellido: 'AAAAAAA',
      fechaNacimento: currentDate,
      sexo: 'AAAAAAA',
      ciudadNacimiento: 'AAAAAAA',
      provinciaNacimiento: 'AAAAAAA',
      idNacionalidad: 0,
      idSerCreador: 0,
      idProvincia: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          fechaNacimento: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Alumno', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          fechaNacimento: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaNacimento: currentDate,
        },
        returnedFromService
      );

      service.create(new Alumno()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Alumno', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          documento: 'BBBBBB',
          idTipoDocumento: 1,
          nombre: 'BBBBBB',
          apellido: 'BBBBBB',
          fechaNacimento: currentDate.format(DATE_TIME_FORMAT),
          sexo: 'BBBBBB',
          ciudadNacimiento: 'BBBBBB',
          provinciaNacimiento: 'BBBBBB',
          idNacionalidad: 1,
          idSerCreador: 1,
          idProvincia: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaNacimento: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Alumno', () => {
      const patchObject = Object.assign(
        {
          documento: 'BBBBBB',
          idTipoDocumento: 1,
          apellido: 'BBBBBB',
          fechaNacimento: currentDate.format(DATE_TIME_FORMAT),
          ciudadNacimiento: 'BBBBBB',
          provinciaNacimiento: 'BBBBBB',
          idNacionalidad: 1,
          idSerCreador: 1,
        },
        new Alumno()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          fechaNacimento: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Alumno', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          documento: 'BBBBBB',
          idTipoDocumento: 1,
          nombre: 'BBBBBB',
          apellido: 'BBBBBB',
          fechaNacimento: currentDate.format(DATE_TIME_FORMAT),
          sexo: 'BBBBBB',
          ciudadNacimiento: 'BBBBBB',
          provinciaNacimiento: 'BBBBBB',
          idNacionalidad: 1,
          idSerCreador: 1,
          idProvincia: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaNacimento: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Alumno', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addAlumnoToCollectionIfMissing', () => {
      it('should add a Alumno to an empty array', () => {
        const alumno: IAlumno = { id: 123 };
        expectedResult = service.addAlumnoToCollectionIfMissing([], alumno);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(alumno);
      });

      it('should not add a Alumno to an array that contains it', () => {
        const alumno: IAlumno = { id: 123 };
        const alumnoCollection: IAlumno[] = [
          {
            ...alumno,
          },
          { id: 456 },
        ];
        expectedResult = service.addAlumnoToCollectionIfMissing(alumnoCollection, alumno);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Alumno to an array that doesn't contain it", () => {
        const alumno: IAlumno = { id: 123 };
        const alumnoCollection: IAlumno[] = [{ id: 456 }];
        expectedResult = service.addAlumnoToCollectionIfMissing(alumnoCollection, alumno);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(alumno);
      });

      it('should add only unique Alumno to an array', () => {
        const alumnoArray: IAlumno[] = [{ id: 123 }, { id: 456 }, { id: 69394 }];
        const alumnoCollection: IAlumno[] = [{ id: 123 }];
        expectedResult = service.addAlumnoToCollectionIfMissing(alumnoCollection, ...alumnoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const alumno: IAlumno = { id: 123 };
        const alumno2: IAlumno = { id: 456 };
        expectedResult = service.addAlumnoToCollectionIfMissing([], alumno, alumno2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(alumno);
        expect(expectedResult).toContain(alumno2);
      });

      it('should accept null and undefined values', () => {
        const alumno: IAlumno = { id: 123 };
        expectedResult = service.addAlumnoToCollectionIfMissing([], null, alumno, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(alumno);
      });

      it('should return initial array if no Alumno is added', () => {
        const alumnoCollection: IAlumno[] = [{ id: 123 }];
        expectedResult = service.addAlumnoToCollectionIfMissing(alumnoCollection, undefined, null);
        expect(expectedResult).toEqual(alumnoCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
