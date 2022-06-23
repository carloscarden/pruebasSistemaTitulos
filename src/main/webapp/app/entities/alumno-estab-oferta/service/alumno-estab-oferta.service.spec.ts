import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAlumnoEstabOferta, AlumnoEstabOferta } from '../alumno-estab-oferta.model';

import { AlumnoEstabOfertaService } from './alumno-estab-oferta.service';

describe('AlumnoEstabOferta Service', () => {
  let service: AlumnoEstabOfertaService;
  let httpMock: HttpTestingController;
  let elemDefault: IAlumnoEstabOferta;
  let expectedResult: IAlumnoEstabOferta | IAlumnoEstabOferta[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AlumnoEstabOfertaService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      idSer: 0,
      idOfertaEducativa: 0,
      idAlumno: 0,
      idEstadoAlumnoEstabOferta: 0,
      fechaInicio: currentDate,
      fechaFin: currentDate,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaFin: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a AlumnoEstabOferta', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaFin: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
        },
        returnedFromService
      );

      service.create(new AlumnoEstabOferta()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AlumnoEstabOferta', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          idSer: 1,
          idOfertaEducativa: 1,
          idAlumno: 1,
          idEstadoAlumnoEstabOferta: 1,
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaFin: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AlumnoEstabOferta', () => {
      const patchObject = Object.assign(
        {
          idAlumno: 1,
        },
        new AlumnoEstabOferta()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AlumnoEstabOferta', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          idSer: 1,
          idOfertaEducativa: 1,
          idAlumno: 1,
          idEstadoAlumnoEstabOferta: 1,
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaFin: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a AlumnoEstabOferta', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addAlumnoEstabOfertaToCollectionIfMissing', () => {
      it('should add a AlumnoEstabOferta to an empty array', () => {
        const alumnoEstabOferta: IAlumnoEstabOferta = { id: 123 };
        expectedResult = service.addAlumnoEstabOfertaToCollectionIfMissing([], alumnoEstabOferta);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(alumnoEstabOferta);
      });

      it('should not add a AlumnoEstabOferta to an array that contains it', () => {
        const alumnoEstabOferta: IAlumnoEstabOferta = { id: 123 };
        const alumnoEstabOfertaCollection: IAlumnoEstabOferta[] = [
          {
            ...alumnoEstabOferta,
          },
          { id: 456 },
        ];
        expectedResult = service.addAlumnoEstabOfertaToCollectionIfMissing(alumnoEstabOfertaCollection, alumnoEstabOferta);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AlumnoEstabOferta to an array that doesn't contain it", () => {
        const alumnoEstabOferta: IAlumnoEstabOferta = { id: 123 };
        const alumnoEstabOfertaCollection: IAlumnoEstabOferta[] = [{ id: 456 }];
        expectedResult = service.addAlumnoEstabOfertaToCollectionIfMissing(alumnoEstabOfertaCollection, alumnoEstabOferta);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(alumnoEstabOferta);
      });

      it('should add only unique AlumnoEstabOferta to an array', () => {
        const alumnoEstabOfertaArray: IAlumnoEstabOferta[] = [{ id: 123 }, { id: 456 }, { id: 71557 }];
        const alumnoEstabOfertaCollection: IAlumnoEstabOferta[] = [{ id: 123 }];
        expectedResult = service.addAlumnoEstabOfertaToCollectionIfMissing(alumnoEstabOfertaCollection, ...alumnoEstabOfertaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const alumnoEstabOferta: IAlumnoEstabOferta = { id: 123 };
        const alumnoEstabOferta2: IAlumnoEstabOferta = { id: 456 };
        expectedResult = service.addAlumnoEstabOfertaToCollectionIfMissing([], alumnoEstabOferta, alumnoEstabOferta2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(alumnoEstabOferta);
        expect(expectedResult).toContain(alumnoEstabOferta2);
      });

      it('should accept null and undefined values', () => {
        const alumnoEstabOferta: IAlumnoEstabOferta = { id: 123 };
        expectedResult = service.addAlumnoEstabOfertaToCollectionIfMissing([], null, alumnoEstabOferta, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(alumnoEstabOferta);
      });

      it('should return initial array if no AlumnoEstabOferta is added', () => {
        const alumnoEstabOfertaCollection: IAlumnoEstabOferta[] = [{ id: 123 }];
        expectedResult = service.addAlumnoEstabOfertaToCollectionIfMissing(alumnoEstabOfertaCollection, undefined, null);
        expect(expectedResult).toEqual(alumnoEstabOfertaCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
